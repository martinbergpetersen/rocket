package org.rocket.buffer;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ChannelState {
  private static Map<String, Integer> channelStates = new HashMap<>();
  private Logger logger = LoggerFactory.getLogger(ChannelState.class);

  public boolean isHigherMessageNumber(String channel, int messageNumber) {
    return getHigestMessageNumber(channel) < messageNumber;
  }

  public void setMessageNumber(String channel, int messageNumber) {
    if (isHigherMessageNumber(channel, messageNumber)) channelStates.put(channel, messageNumber);
  }

  private int getHigestMessageNumber(String channel) {
    return channelStates.getOrDefault(channel, 0);
  }
}
