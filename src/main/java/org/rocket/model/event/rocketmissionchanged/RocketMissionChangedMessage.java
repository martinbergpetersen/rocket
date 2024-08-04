package org.rocket.model.event.rocketmissionchanged;

public class RocketMissionChangedMessage {
  private String newMission;

  public String getNewMission() {
    return newMission;
  }

  public void setNewMission(String newMission) {
    this.newMission = newMission;
  }

  @Override
  public String toString() {
    return "RocketMissionChangedMessage{" + "newMission='" + newMission + '\'' + '}';
  }
}
