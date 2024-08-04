package org.rocket.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.mapper.RocketMissionChangedEventMapper;
import org.rocket.model.Rocket;
import org.rocket.unit.shared.FileReader;

@ExtendWith(MockitoExtension.class)
class RocketMissionChangedEventMapperTest {

  @Test
  void testMapperWithNewMission() throws IOException {
    var id = "193273a9-c9cf-404a-8f83-838e71d9ae67";
    var event = new FileReader().from("src/test/resources/rocket-mission-changed-event.json");
    var rocket = new RocketMissionChangedEventMapper().from(new Rocket(id, null, 0, null), event);

    assertEquals("SHUTTLE_MIR", rocket.getMission());
  }
}
