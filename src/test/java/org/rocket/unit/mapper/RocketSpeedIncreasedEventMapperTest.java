package org.rocket.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.mapper.RocketSpeedIncreasedEventMapper;
import org.rocket.model.Rocket;
import org.rocket.unit.shared.FileReader;

@ExtendWith(MockitoExtension.class)
class RocketSpeedIncreasedEventMapperTest {

  @Test
  void testMapperWithIncreasedSpeed() throws IOException {
    var id = "193275a9-c9cf-404a-8f83-838e71d9ae67";

    var event = new FileReader().from("src/test/resources/rocket-speed-increased-event.json");
    var rocket = new RocketSpeedIncreasedEventMapper().from(new Rocket(id, null, 500, null), event);

    assertEquals(2000, rocket.getSpeed());
  }
}
