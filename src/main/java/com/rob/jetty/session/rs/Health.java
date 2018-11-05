package com.rob.jetty.session.rs;

import javax.ws.rs.Path;

/**
 * Health is a JAX-RS endpoint for showing the health of the application.
 * @author Rob Benton
 */
@Path(Health.PATH)
public class Health
{
    public static final String PATH = "health";


}
