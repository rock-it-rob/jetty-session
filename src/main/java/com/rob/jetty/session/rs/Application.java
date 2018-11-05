package com.rob.jetty.session.rs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Application is the main Jersey {@link ResourceConfig} for the application.
 *
 * @author Rob Benton
 */
@Component
public class Application extends ResourceConfig
{
    public Application()
    {
        register(Health.class);
    }
}
