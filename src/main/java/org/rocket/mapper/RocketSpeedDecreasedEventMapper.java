package org.rocket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rocket.model.Rocket;
import org.rocket.model.event.rocketspeed.RocketSpeedDecreaseEvent;

public class RocketSpeedDecreasedEventMapper {
  public Rocket from(Rocket rocket, String event)
      throws JsonMappingException, JsonProcessingException {

    var rocketSpeedDecreasedEvent =
        new ObjectMapper().readValue(event, RocketSpeedDecreaseEvent.class);
    rocket.setSpeed(getNewSpeed(rocket, rocketSpeedDecreasedEvent));
    return rocket;
  }

  private int getNewSpeed(Rocket rocket, RocketSpeedDecreaseEvent rocketSpeedDecreaseEvent) {
    return rocket.getSpeed() - rocketSpeedDecreaseEvent.getMessage().getBy();
  }
}
