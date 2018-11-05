package com.rob.jetty.session;

import org.eclipse.jetty.nosql.mongodb.MongoSessionDataStore;
import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Config is the main configuration class for the application.
 *
 * @author Rob Benton
 */
@SpringBootApplication
public class Config
{
    public static void main(String[] args)
    {
        SpringApplication.run(Config.class, args);
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory()
    {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addConfigurations(
            new MyJettyConfig()
        );
        return factory;
    }


    static final class MyJettyConfig extends AbstractConfiguration
    {
        @Override
        public void configure(WebAppContext context)
        {
            final SessionHandler sessionHandler = context.getSessionHandler();
            final MongoSessionDataStore dataStore = new MongoSessionDataStore();
            final NullSessionCache sessionCache = new NullSessionCache(sessionHandler);
            sessionCache.setSessionDataStore(dataStore);
            sessionHandler.setSessionCache(new NullSessionCache(sessionHandler)); // no idea if this is right. Seems weird.
        }
    }
}