package org.rocket.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.rocket.RocketOrderBy;
import org.rocket.RocketOrderByType;
import org.rocket.RocketRepository;
import org.rocket.model.Rocket;

@Path("/api/rockets")
public class RocketResource {
  @Inject RocketRepository repository;

  @GET()
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Returns a rocket based on an id")
  @APIResponse(responseCode = "404", description = "Rocket not found")
  @APIResponse(
      responseCode = "200",
      description = "Rocket found",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON,
              schema = @Schema(implementation = Rocket.class, type = SchemaType.OBJECT)))
  public Response get(@PathParam("id") String id) {
    try {
      return Response.ok(repository.get(id)).build();
    } catch (EntityNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @GET()
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Returns all Rockets")
  @APIResponse(
      responseCode = "200",
      description = "Rocket found",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON,
              schema = @Schema(implementation = Rocket.class, type = SchemaType.ARRAY)))
  public Response getAll(
      @Parameter(description = "Used to order the rockets", example = "MISSION")
          @QueryParam("orderBy")
          final RocketOrderByType orderBy) {

    try {
      var rockets = RocketOrderBy.apply(orderBy, repository.getAll());
      return Response.ok(rockets).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }
}
