package org.rocket.model.event.rocketmissionchanged;

import org.rocket.model.event.Metadata;

public class RocketMissionChangedEvent {


  private Metadata metadata;
  private RocketMissionChangedMessage message;

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public RocketMissionChangedMessage getMessage() {
    return message;
  }

  public void setMessage(RocketMissionChangedMessage message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Event{" + "metadata=" + metadata + ", message=" + message + '}';
  }
}
