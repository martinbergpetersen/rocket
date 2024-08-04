package org.rocket.model;

public class Rocket {
  private String id;
  private String type;
  private int speed;
  private String mission;
  private RocketStatus status;

  public Rocket() {}

  public Rocket(String id, String type, int speed, String mission) {
    this.id = id;
    this.type = type;
    this.speed = speed;
    this.mission = mission;
    this.status = new RocketStatus();
  }

  public Rocket(String id, String type, int speed, String mission, RocketStatus status) {
    this.id = id;
    this.type = type;
    this.speed = speed;
    this.mission = mission;
    this.status = status;
  }

  public RocketStatus getStatus() {
    return status;
  }

  public void setStatus(RocketStatus status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public String getMission() {
    return mission;
  }

  public void setMission(String mission) {
    this.mission = mission;
  }

  @Override
  public String toString() {
    return "Rocket [id="
        + id
        + ", type="
        + type
        + ", speed="
        + speed
        + ", mission="
        + mission
        + ", status="
        + status
        + "]";
  }
}
