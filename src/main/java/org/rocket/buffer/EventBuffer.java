package org.rocket.buffer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.rocket.RocketRepository;
import org.rocket.model.event.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EventBuffer {
  private Logger logger = LoggerFactory.getLogger(EventBuffer.class);

  private int eventCount = 0;
  private int maxEventsBeforeFlush = 20;

  private final Lock lock = new ReentrantLock();
  private final RocketRepository rocketRepository;
  private final ChannelState channelState;

  private List<EventState> eventStates = new ArrayList<>();
  private Map<Integer, Metadata> eventHashes = new HashMap<>();

  @Inject
  public EventBuffer(ChannelState channelState, RocketRepository rocketRepository) {
    this.channelState = channelState;
    this.rocketRepository = rocketRepository;
  }

  public void setMaxEventsBeforeFlush(int maxEventsBeforeFlush) {
    this.maxEventsBeforeFlush = maxEventsBeforeFlush;
  }

  public void add(String event) throws JsonMappingException, JsonProcessingException {
    var eventState = new EventState(event);
    try {
      lock.lock();
      logger.info("Adding event: {}", eventState.getEvent());
      if (shouldAdd(eventState)) add(eventState);
    } finally {
      if (shouldFlush()) flush();
      lock.unlock();
    }
  }

  public void flush() throws JsonMappingException, JsonProcessingException {
    logger.info("Flushing buffer");
    sync();
    eventHashes.clear();
    eventStates = new ArrayList<>();
  }

  private void add(EventState eventState) {
    eventStates.add(eventState);
    channelState.setMessageNumber(
        eventState.getMetadata().getChannel(), eventState.getMetadata().getMessageNumber());
    eventHashes.put(eventState.getHashCode(), eventState.getMetadata());
    eventCount++;
  }

  private void sync() throws JsonMappingException, JsonProcessingException {
    logger.info("Syncing eventStates");
    sortEventsByTimeStamp();
    rocketRepository.updateRockets(eventStates);
  }

  private void sortEventsByTimeStamp() {
    eventStates.sort(
        (e1, e2) -> e1.getMetadata().getMessageTime().compareTo(e2.getMetadata().getMessageTime()));
  }

  private boolean shouldAdd(EventState eventState) {
    var hashCode = eventState.getHashCode();
    var isNewMessageNumberHigher =
        channelState.isHigherMessageNumber(
            eventState.getMetadata().getChannel(), eventState.getMetadata().getMessageNumber());
    var shouldAdd = !eventHashes.containsKey(hashCode) && (isNewMessageNumberHigher);

    logger.info("Event hash code exists: {}", eventHashes.containsKey(hashCode));
    logger.info("Is new message number higher: {}", isNewMessageNumberHigher);
    logger.info("Should this be added? {}", shouldAdd);

    return shouldAdd;
  }

  private boolean shouldFlush() {
    return (eventCount % maxEventsBeforeFlush) == 0;
  }
}
