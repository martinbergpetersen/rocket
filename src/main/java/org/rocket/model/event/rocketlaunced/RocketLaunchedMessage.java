package org.rocket.model.event.rocketlaunced;

public class RocketLaunchedMessage {
  private String type;
  private int launchSpeed;
  private String mission;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getLaunchSpeed() {
    return launchSpeed;
  }

  public void setLaunchSpeed(int launchSpeed) {
    this.launchSpeed = launchSpeed;
  }

  public String getMission() {
    return mission;
  }

  public void setMission(String mission) {
    this.mission = mission;
  }

  @Override
  public String toString() {
    return "Message{"
        + "type='"
        + type
        + '\''
        + ", launchSpeed="
        + launchSpeed
        + ", mission='"
        + mission
        + '\''
        + '}';
  }
}
