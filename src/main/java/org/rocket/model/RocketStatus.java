package org.rocket.model;

public class RocketStatus {
  private boolean exploded = false;
  private String reason;

  public RocketStatus() {}

  public RocketStatus(boolean exploded, String reason) {
    this.exploded = exploded;
    this.reason = reason;
  }

  public boolean isExploded() {
    return exploded;
  }

  public void setExploded(boolean exploded) {
    this.exploded = exploded;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "RocketStatus{" + "exploded=" + exploded + ", reason='" + reason + '\'' + '}';
  }
}
