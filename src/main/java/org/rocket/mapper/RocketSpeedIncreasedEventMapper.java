package org.rocket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rocket.model.Rocket;
import org.rocket.model.event.rocketspeed.RocketSpeedIncreasedEvent;

public class RocketSpeedIncreasedEventMapper {

  public Rocket from(Rocket rocket, String event)
      throws JsonMappingException, JsonProcessingException {

    var rocketSpeedIncreasedEvent =
        new ObjectMapper().readValue(event, RocketSpeedIncreasedEvent.class);
    rocket.setSpeed(getNewSpeed(rocket, rocketSpeedIncreasedEvent));
    return rocket;
  }

  private int getNewSpeed(Rocket rocket, RocketSpeedIncreasedEvent rocketSpeedIncreasedEvent) {
    return rocket.getSpeed() + rocketSpeedIncreasedEvent.getMessage().getBy();
  }
}
