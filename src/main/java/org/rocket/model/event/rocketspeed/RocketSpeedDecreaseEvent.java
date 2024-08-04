package org.rocket.model.event.rocketspeed;

import org.rocket.model.event.Metadata;

public class RocketSpeedDecreaseEvent {

  private Metadata metadata;
  private RocketSpeedMessage message;

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public RocketSpeedMessage getMessage() {
    return message;
  }

  public void setMessage(RocketSpeedMessage message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Event{" + "metadata=" + metadata + ", message=" + message + '}';
  }
}
