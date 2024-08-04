package org.rocket.model.event.rocketlaunced;

import org.rocket.model.event.Metadata;

public class RocketLaunchedEvent {

  private Metadata metadata;
  private RocketLaunchedMessage message;

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public RocketLaunchedMessage getMessage() {
    return message;
  }

  public void setMessage(RocketLaunchedMessage message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Event{" + "metadata=" + metadata + ", message=" + message + '}';
  }
}
