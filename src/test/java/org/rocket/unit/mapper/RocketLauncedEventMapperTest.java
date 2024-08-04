package org.rocket.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.mapper.RocketLaunchedEventMapper;
import org.rocket.unit.shared.FileReader;

@ExtendWith(MockitoExtension.class)
class RocketLauncedEventMapperTest {

  @Test
  void testMapperWithNewRocket() throws IOException {

    var event = new FileReader().from("src/test/resources/rocket-launch-event.json");
    var rocket = new RocketLaunchedEventMapper().from(event);

    assertEquals("193272a9-c9cf-404a-8f83-838e71d9ae67", rocket.getId());
    assertEquals("Falcon-9", rocket.getType());
    assertEquals("ARTEMIS", rocket.getMission());
    assertEquals(500, rocket.getSpeed());
    assertEquals(false, rocket.getStatus().isExploded());
  }
}
