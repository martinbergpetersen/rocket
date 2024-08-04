package org.rocket.unit.eventbuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.arc.Arc;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.rocket.RocketFactory;
import org.rocket.RocketInMemoryDb;
import org.rocket.RocketRepository;
import org.rocket.buffer.ChannelState;
import org.rocket.buffer.EventBuffer;
import org.rocket.unit.shared.FileReader;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EventBufferTest {

  static EventBuffer eventBuffer;
  static RocketRepository rocketRepository;

  // Clear each buffer before each tests
  @BeforeEach
  void beforeEach() {
    var container = Arc.container();
    if (container != null) {
      container.getActiveContext(ApplicationScoped.class);
    }
    rocketRepository = new RocketRepository(new RocketInMemoryDb(), new RocketFactory());
    eventBuffer = new EventBuffer(new ChannelState(), rocketRepository);
  }

  @Test
  void testAddToEventBuffer() throws IOException {
    var event = new FileReader().from("src/test/resources/rocket-launch-event.json");
    eventBuffer.add(event);
    eventBuffer.flush();

    assertEquals(1, rocketRepository.getAll().size());
  }

  @Test
  void testAddMultipleOfSameEventAndDiscard() throws IOException {
    var event = new FileReader().from("src/test/resources/rocket-launch-event.json");
    eventBuffer.add(event);
    eventBuffer.add(event);
    eventBuffer.add(event);
    eventBuffer.flush();

    assertEquals(1, rocketRepository.getAll().size());
  }

  @Test
  void testAddEventsWithBufferGettingFlushed() throws IOException {
    var event = new FileReader().from("src/test/resources/rocket-launch-event.json");
    eventBuffer.setMaxEventsBeforeFlush(1);
    eventBuffer.add(event);

    assertEquals(1, rocketRepository.getAll().size());
  }
}
