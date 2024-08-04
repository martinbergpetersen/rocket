package org.rocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.enterprise.context.ApplicationScoped;
import org.primefaces.shaded.json.JSONObject;
import org.rocket.buffer.EventState;
import org.rocket.mapper.RocketExplodedEventMapper;
import org.rocket.mapper.RocketLaunchedEventMapper;
import org.rocket.mapper.RocketMissionChangedEventMapper;
import org.rocket.mapper.RocketSpeedDecreasedEventMapper;
import org.rocket.mapper.RocketSpeedIncreasedEventMapper;
import org.rocket.model.Rocket;

@ApplicationScoped
public class RocketFactory {

  public Rocket create(EventState eventState) throws JsonMappingException, JsonProcessingException {
    var event = eventState.getEvent();
    var messageType = getMessageType(event);

    if (messageType.equals("RocketLaunched")) {
      return new RocketLaunchedEventMapper().from(event);
    }
    throw new IllegalArgumentException("Unknown message type: " + messageType);
  }

  public Rocket update(EventState eventState, Rocket rocket)
      throws JsonMappingException, JsonProcessingException {
    var event = eventState.getEvent();
    var messageType = getMessageType(event);

    if (messageType.equals("RocketSpeedIncreased")) {
      return new RocketSpeedIncreasedEventMapper().from(rocket, event);
    }

    if (messageType.equals("RocketSpeedDecreased")) {
      return new RocketSpeedDecreasedEventMapper().from(rocket, event);
    }

    if (messageType.equals("RocketExploded")) {
      return new RocketExplodedEventMapper().from(rocket, event);
    }

    if (messageType.equals("RocketMissionChanged")) {
      return new RocketMissionChangedEventMapper().from(rocket, event);
    }
    throw new IllegalArgumentException("Unknown message type: " + messageType);
  }

  private String getMessageType(String event) {
    return new JSONObject(event).getJSONObject("metadata").getString("messageType");
  }
}
