package org.rocket.buffer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.primefaces.shaded.json.JSONObject;
import org.rocket.model.event.Metadata;

public class EventState {
  private String event;
  private Metadata metadata;
  private int hashCode;

  public EventState(String event) throws JsonMappingException, JsonProcessingException {
    this.metadata = getMetadata(event);
    this.event = event;
    this.hashCode = createHashCode(this.metadata);
  }

  private Metadata getMetadata(String event) throws JsonMappingException, JsonProcessingException {
    var data = new JSONObject(event).getJSONObject("metadata").toString();
    return new ObjectMapper().readValue(data, Metadata.class);
  }

  public String getEvent() {
    return this.event;
  }

  public Metadata getMetadata() {
    return this.metadata;
  }

  public int getHashCode() {
    return this.hashCode;
  }

  private int createHashCode(Metadata metadata) {
    return this.event.hashCode();
  }
}
