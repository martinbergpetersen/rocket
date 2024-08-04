package org.rocket.model.event.rocketspeed;

public class RocketSpeedMessage {
  private int by;

  public int getBy() {
    return by;
  }

  public void setBy(int by) {
    this.by = by;
  }

  @Override
  public String toString() {
    return "Message{" + "by=" + by + '}';
  }
}
