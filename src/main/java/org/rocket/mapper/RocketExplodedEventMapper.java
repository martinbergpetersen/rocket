package org.rocket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rocket.model.Rocket;
import org.rocket.model.RocketStatus;
import org.rocket.model.event.rocketexploded.RocketExplodedEvent;

public class RocketExplodedEventMapper {

  public Rocket from(Rocket rocket, String event)
      throws JsonMappingException, JsonProcessingException {

    var rocketExplodedEvent = new ObjectMapper().readValue(event, RocketExplodedEvent.class);
    rocket.setStatus(new RocketStatus(true, rocketExplodedEvent.getMessage().getReason()));
    return rocket;
  }
}
