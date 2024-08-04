package org.rocket.model.event.rocketexploded;

public class RocketExplodedMessage {
  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "Message{" + "reason=" + reason + '}';
  }
}
