package org.rocket.model.event.rocketexploded;

import org.rocket.model.event.Metadata;

public class RocketExplodedEvent {

  private Metadata metadata;
  private RocketExplodedMessage message;

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public RocketExplodedMessage getMessage() {
    return message;
  }

  public void setMessage(RocketExplodedMessage message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Event{" + "metadata=" + metadata + ", message=" + message + '}';
  }
}
