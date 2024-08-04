package org.rocket.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.RocketRepository;
import org.rocket.model.Rocket;
import org.rocket.resources.RocketResource;

@ExtendWith(MockitoExtension.class)
class RocketResourceTest {

  @Mock RocketRepository repository;

  @InjectMocks RocketResource service;

  @Test
  void testGetWithFoundRocket() {
    var id = "1234";
    var dummyRocket = new Rocket(id, "Skylab", 5000, "Thor-Agena");

    when(repository.get(id)).thenReturn(dummyRocket);

    var rocket = (Rocket) service.get(id).getEntity();

    assertEquals("1234", rocket.getId());
  }

  @Test
  void testGetEntityNotFoundException() {
    var id = "1234";
    when(repository.get(id)).thenThrow(new EntityNotFoundException("Rocket not found"));

    var response = (Response) service.get(id);

    assertEquals(404, response.getStatusInfo().getStatusCode());
  }

  @SuppressWarnings("unchecked")
  @Test
  void testGetAll() {

    when(repository.getAll())
        .thenReturn(
            List.of(
                new Rocket("1234", "Skylab", 3000, "Thor-Agena"),
                new Rocket("4566", "Juno-1", 5000, "Gemini")));

    var rockets = (List<? extends Rocket>) service.getAll(null).getEntity();

    assertEquals(2, rockets.size());
  }

  @SuppressWarnings("unchecked")
  @Test
  void testGetWithEmptyList() {
    when(repository.getAll()).thenReturn(List.of());

    var rockets = (List<Rocket>) service.getAll(null).getEntity();

    assertEquals(0, rockets.size());
  }
}
