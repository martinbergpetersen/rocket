package org.rocket.model.event;

import java.util.Date;

public class Metadata {
  private String channel;
  private int messageNumber;
  private Date messageTime;
  private String messageType;

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public int getMessageNumber() {
    return messageNumber;
  }

  public void setMessageNumber(int messageNumber) {
    this.messageNumber = messageNumber;
  }

  public Date getMessageTime() {
    return messageTime;
  }

  public void setMessageTime(Date messageTime) {
    this.messageTime = messageTime;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  @Override
  public String toString() {
    return "Metadata{"
        + "channel='"
        + channel
        + '\''
        + ", messageNumber="
        + messageNumber
        + ", messageTime="
        + messageTime
        + ", messageType='"
        + messageType
        + '\''
        + '}';
  }
}
