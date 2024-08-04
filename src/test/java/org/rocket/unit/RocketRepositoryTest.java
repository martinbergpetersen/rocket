package org.rocket.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.quarkus.arc.Arc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.RocketFactory;
import org.rocket.RocketInMemoryDb;
import org.rocket.RocketRepository;
import org.rocket.model.Rocket;

@ExtendWith(MockitoExtension.class)
class RocketRepositoryTest {

  static RocketInMemoryDb rocketInMemoryDb;
  static RocketRepository rocketRepository;

  // Clear each buffer before each tests
  @BeforeEach
  void beforeEach() {
    var container = Arc.container();
    if (container != null) {
      container.getActiveContext(ApplicationScoped.class);
    }
    rocketInMemoryDb = new RocketInMemoryDb();
    rocketRepository = new RocketRepository(rocketInMemoryDb, new RocketFactory());
  }

  @Test
  void testGet() {
    var id = "1234";
    var dummyRocket = new Rocket(id, "Skylab", 5000, "Thor-Agena");

    var db = mock(RocketInMemoryDb.class);
    when(db.get(id)).thenReturn(dummyRocket);

    var rocketRepository = new RocketRepository(db, new RocketFactory());
    var rocket = rocketRepository.get(id);
    assertEquals("1234", rocket.getId());
  }

  @Test
  void testGetWithEntityNotFoundException() {
    var id = "1234";
    var db = mock(RocketInMemoryDb.class);
    when(db.get(id)).thenThrow(new EntityNotFoundException("Rocket not found"));

    var rocketRepository = new RocketRepository(db, new RocketFactory());
    assertThrows(
        EntityNotFoundException.class,
        () -> {
          rocketRepository.get(id);
        });
  }

  @Test
  void testGetAll() {
    var db = mock(RocketInMemoryDb.class);
    when(db.getAll())
        .thenReturn(
            List.of(
                new Rocket("1234", "Skylab", 3000, "Thor-Agena"),
                new Rocket("4566", "Juno-1", 5000, "Gemini")));

    var rocketRepository = new RocketRepository(db, new RocketFactory());
    var rockets = rocketRepository.getAll();
    assertEquals(2, rockets.size());
  }

  @Test
  void testGetAllWithEmptyList() {
    var db = mock(RocketInMemoryDb.class);
    when(db.getAll()).thenReturn(List.of());

    var rocketRepository = new RocketRepository(db, new RocketFactory());
    var rockets = rocketRepository.getAll();
    assertEquals(0, rockets.size());
  }

  @Test
  void testCreate() {
    var rocketRepository = new RocketRepository(rocketInMemoryDb, new RocketFactory());
    rocketRepository.createOrUpdate(new Rocket("1234", "Skylab", 5000, "Thor-Agena"));
    var rocket = rocketRepository.getAll();
    assertEquals(1, rocket.size());
  }
}
