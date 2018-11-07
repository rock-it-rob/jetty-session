package com.rob.jetty.session.rs;

import com.rob.jetty.session.rs.resource.Health;
import com.rob.jetty.session.rs.resource.MongoSessionInfo;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Application is the main Jersey {@link ResourceConfig} for the application.
 *
 * @author Rob Benton
 */
@Component
@ApplicationPath(Application.PATH)
public class Application extends ResourceConfig
{
    public static final String PATH = "/rs";

    public Application()
    {
        register(Health.class);
        register(MongoSessionInfo.class);
    }
}
