package org.rocket.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.rocket.buffer.EventBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/messages")
public class MessageResource {
  @Inject UriInfo uriInfo;
  @Inject EventBuffer eventBuffer;
  private Logger logger = LoggerFactory.getLogger(MessageResource.class);

  @POST()
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "FILL OUT")
  @APIResponse(
      responseCode = "201",
      description = "Created",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON,
              schema = @Schema(implementation = String.class, type = SchemaType.OBJECT)))
  public Response post(String event) throws JsonMappingException, JsonProcessingException {
    eventBuffer.add(event);

    return Response.noContent().build();
  }
}
