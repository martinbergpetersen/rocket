package org.rocket;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.rocket.model.Rocket;

@ApplicationScoped
public class RocketInMemoryDb {

  private static final List<Rocket> rockets = new ArrayList<>();

  public Rocket get(String id) {
    return rockets.stream()
        .filter(rocket -> rocket.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("Rocket not found"));
  }

  public List<Rocket> getAll() {
    return rockets;
  }

  public void createOrUpdate(Rocket rocket) {
    rockets.removeIf(r -> r.getId().equals(rocket.getId()));
    rockets.add(rocket);
  }

  public void clear() {
    rockets.clear();
  }
}
