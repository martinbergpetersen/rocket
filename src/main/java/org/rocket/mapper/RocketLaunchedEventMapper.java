package org.rocket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rocket.model.Rocket;
import org.rocket.model.event.rocketlaunced.RocketLaunchedEvent;

public class RocketLaunchedEventMapper {

  public Rocket from(String event) throws JsonMappingException, JsonProcessingException {

    var rocketLaunchedEvent = new ObjectMapper().readValue(event, RocketLaunchedEvent.class);
    return new Rocket(
        rocketLaunchedEvent.getMetadata().getChannel(),
        rocketLaunchedEvent.getMessage().getType(),
        rocketLaunchedEvent.getMessage().getLaunchSpeed(),
        rocketLaunchedEvent.getMessage().getMission());
  }
}
