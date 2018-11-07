package com.rob.jetty.session.rs.resource;

import com.rob.jetty.session.profile.MongoDBJettySession;
import com.rob.jetty.session.rs.response.MongoSessionInfoResponse;
import com.rob.jetty.session.rs.response.entity.MongoSessionInfoEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * MongoSessionInfo is a JAX-RS resource returning information about the
 * configuration of the MongoDB session data store configuration for the
 * embedded Jetty instance.
 *
 * @author Rob Benton
 */
@Component
@Path(MongoSessionInfo.PATH)
public final class MongoSessionInfo implements ApplicationContextAware
{
    public static final String PATH = "mongoSession";

    private ApplicationContext applicationContext;

    private boolean enabled = false;

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;

    @Value("${mongo.dbname}")
    private String dbName;

    @Value("${mongo.collection.name}")
    private String collectionName;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MongoSessionInfoResponse get()
    {
        return new MongoSessionInfoResponse(
            Response.Status.OK.getStatusCode(),
            new MongoSessionInfoEntity()
                .enabled(enabled)
                .host(host)
                .port(port)
                .databaseName(dbName)
                .collectionName(collectionName)
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void postConstruct()
    {
        // Check to see if the mongo session profile is set. If it is then
        // we are storing sessions in mongo and our enabled flag should be set
        // to true. Otherwise leave it to the default of 'false'.
        for (String p : applicationContext.getEnvironment().getActiveProfiles())
        {
            if (p.equals(MongoDBJettySession.PROFILE))
            {
                enabled = true;
                break;
            }
        }
    }
}
