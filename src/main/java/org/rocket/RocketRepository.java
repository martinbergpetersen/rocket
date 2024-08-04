package org.rocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.rocket.buffer.EventState;
import org.rocket.model.Rocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RocketRepository {
  private Logger logger = LoggerFactory.getLogger(RocketInMemoryDb.class);

  private RocketInMemoryDb rocketInMemoryDb;
  private RocketFactory rocketFactory;

  @Inject
  public RocketRepository(RocketInMemoryDb rocketInMemoryDb, RocketFactory rocketFactory) {
    this.rocketInMemoryDb = rocketInMemoryDb;
    this.rocketFactory = rocketFactory;
  }

  public Rocket get(String id) {
    try {
      return rocketInMemoryDb.get(id);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Rocket not found");
    }
  }

  public List<Rocket> getAll() {
    return rocketInMemoryDb.getAll();
  }

  public void createOrUpdate(Rocket rocket) {
    rocketInMemoryDb.createOrUpdate(rocket);
  }

  public void clear() {
    rocketInMemoryDb.clear();
  }

  public void updateRockets(List<EventState> eventStates)
      throws JsonMappingException, JsonProcessingException {

    for (var eventState : eventStates) {
      logger.info(
          "Processing event into a rocket: {} {} {}",
          eventState.getMetadata().getChannel(),
          eventState.getMetadata().getMessageNumber(),
          eventState.getMetadata().getMessageTime());
      Rocket updatedRocket;
      try {
        var rocket = rocketInMemoryDb.get(eventState.getMetadata().getChannel());
        updatedRocket = rocketFactory.update(eventState, rocket);
      } catch (EntityNotFoundException e) {
        updatedRocket = rocketFactory.create(eventState);
      }
      rocketInMemoryDb.createOrUpdate(updatedRocket);
    }
  }
}
