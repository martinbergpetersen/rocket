package org.rocket;

import java.util.Comparator;
import java.util.List;
import org.rocket.model.Rocket;

public class RocketOrderBy {
  public static List<Rocket> apply(RocketOrderByType rocketOrderByType, List<Rocket> rockets) {
    if (rocketOrderByType == RocketOrderByType.SPEED) {
      rockets.sort(Comparator.comparing(r -> r.getSpeed(), Comparator.reverseOrder()));
      return rockets;
    }
    if (rocketOrderByType == RocketOrderByType.TYPE) {
      rockets.sort(Comparator.comparing(r -> r.getType()));
      return rockets;
    }
    if (rocketOrderByType == RocketOrderByType.MISSION) {
      rockets.sort(Comparator.comparing(r -> r.getMission()));
      return rockets;
    }
    return rockets;
  }
}
