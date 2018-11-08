package com.rob.jetty.profile;

import com.rob.jetty.configuration.MongoSessionJettyConfiguration;
import com.rob.jetty.session.MongoSessionDataExtractor;
import com.rob.jetty.session.SessionDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * MongoDBJettySession configures the spring beans and configuration for the
 * profile {@link #PROFILE}.
 * <p>
 * This profile configures the embedded Jetty web server instance to use
 * a MongoDB instance for its {@link org.eclipse.jetty.server.session.SessionDataStore}.
 * The {@link org.eclipse.jetty.server.session.NullSessionCache} implementation
 * is used for the session cache.
 * <p>
 * Usage of this configuration class/profile allows for multiple instances to
 * run and use the same consistent session object for an {@link javax.servlet.http.HttpSession}.
 *
 * <h1>Additional Configuration Required</h1>
 * In order to allow multiple instances of Jetty to run and use the same
 * session data store each instance must use a unique value for their
 * {@link org.eclipse.jetty.server.SessionIdManager}. There are several ways to
 * do this. See the relevant documentation at:
 * <p>
 * https://www.eclipse.org/jetty/documentation/current/session-configuration-housekeeper.html
 *
 * @author Rob Benton
 */
@Profile(MongoDBJettySession.PROFILE)
@Configuration
public class MongoDBJettySession
{
    public static final String PROFILE = "mongo-jetty-session";

    private static final Logger log = LoggerFactory.getLogger(MongoDBJettySession.class);

    @Value("${mongo.host}")
    private String mongoHost;

    @Value("${mongo.port}")
    private Integer mongoPort;

    @Value("${mongo.dbname}")
    private String mongoDbname;

    @Value("${mongo.collection.name}")
    private String mongoCollectionName;

    /**
     * Configures the Jetty factory for mongo session usage.
     *
     * @return WebServerFactoryCustomizer<ConfigurableJettyWebServerFactory>
     */
    @Bean
    public WebServerFactoryCustomizer<JettyServletWebServerFactory> webServerFactoryCustomizer()
    {
        assert (mongoHost != null);
        assert (mongoPort != null);
        assert (mongoDbname != null);
        assert (mongoCollectionName != null);

        return (factory) ->
            factory.addConfigurations(
                new MongoSessionJettyConfiguration(mongoHost, mongoPort, mongoDbname, mongoCollectionName)
            );
    }

    /**
     * Create the session extractor bean.
     *
     * @return SessionDataExtractor
     */
    @Bean
    public SessionDataExtractor sessionDataExtractor()
    {
        return new MongoSessionDataExtractor();
    }
}
