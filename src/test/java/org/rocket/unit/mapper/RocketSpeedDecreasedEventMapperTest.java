package org.rocket.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.mapper.RocketSpeedDecreasedEventMapper;
import org.rocket.model.Rocket;
import org.rocket.unit.shared.FileReader;

@ExtendWith(MockitoExtension.class)
class RocketSpeedDecreasedEventMapperTest {

  @Test
  void testMapperWithDecreasedSpeed() throws IOException {
    var id = "193274a9-c9cf-404a-8f83-838e71d9ae67";

    var event = new FileReader().from("src/test/resources/rocket-speed-decreased-event.json");
    var rocket = new RocketSpeedDecreasedEventMapper().from(new Rocket(id, null, 500, null), event);

    assertEquals(-2000, rocket.getSpeed());
  }
}
