package com.rob.jetty.rs.resource;

import com.rob.jetty.rs.response.HealthResponse;
import com.rob.jetty.rs.response.entity.HealthEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Health is a JAX-RS endpoint for showing the health of the application.
 *
 * @author Rob Benton
 */
@Path(Health.PATH)
public class Health
{
    public static final String PATH = "health";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthResponse get()
    {
        return new HealthResponse(
            Response.Status.OK.getStatusCode(),
            new HealthEntity(true)
        );
    }
}
