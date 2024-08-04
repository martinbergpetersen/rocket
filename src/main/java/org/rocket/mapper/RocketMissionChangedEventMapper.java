package org.rocket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rocket.model.Rocket;
import org.rocket.model.event.rocketmissionchanged.RocketMissionChangedEvent;

public class RocketMissionChangedEventMapper {

  public Rocket from(Rocket rocket, String event)
      throws JsonMappingException, JsonProcessingException {

    var rocketMissionChangedEvent =
        new ObjectMapper().readValue(event, RocketMissionChangedEvent.class);
    rocket.setMission(rocketMissionChangedEvent.getMessage().getNewMission());
    return rocket;
  }
}
