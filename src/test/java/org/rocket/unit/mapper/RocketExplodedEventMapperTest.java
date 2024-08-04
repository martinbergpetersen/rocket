package org.rocket.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.mapper.RocketExplodedEventMapper;
import org.rocket.model.Rocket;
import org.rocket.unit.shared.FileReader;

@ExtendWith(MockitoExtension.class)
class RocketExplodedEventMapperTest {

  @Test
  void testMapperWithExplodedStatusTrue() throws IOException {
    var id = "193271a9-c9cf-404a-8f83-838e71d9ae67";

    var event = new FileReader().from("src/test/resources/rocket-exploded-event.json");
    var rocket = new RocketExplodedEventMapper().from(new Rocket(id, null, 0, null), event);

    assertEquals(true, rocket.getStatus().isExploded());
    assertEquals("PRESSURE_VESSEL_FAILURE", rocket.getStatus().getReason());
  }
}
