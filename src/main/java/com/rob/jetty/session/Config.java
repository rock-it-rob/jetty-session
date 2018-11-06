package com.rob.jetty.session;

import org.eclipse.jetty.nosql.mongodb.MongoSessionDataStoreFactory;
import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.SessionDataStore;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public static void main(String[] args)
    {
        SpringApplication.run(Config.class, args);
    }

    @Value("${mongo.host}")
    private String mongoHost;

    @Value("${mongo.dbname}")
    private String mongoDbname;

    @Value("${mongo.collection.name}")
    private String mongoCollectionName;

    @Bean
    public ServletWebServerFactory servletWebServerFactory()
    {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addConfigurations(
            new MongoSessionDataStoreConfig(mongoHost, mongoDbname, mongoCollectionName)
        );
        return factory;
    }


    static final class MongoSessionDataStoreConfig extends AbstractConfiguration
    {
        private final String host;
        private final String dbname;
        private final String collectionName;

        MongoSessionDataStoreConfig(String host, String dbname, String collectionName)
        {
            this.host = host;
            this.dbname = dbname;
            this.collectionName = collectionName;
        }

        @Override
        public void configure(WebAppContext context) throws Exception
        {
            log.debug("Utilizing MongoDB for session data store @ " + host + ". dbname: " + dbname);

            //
            // TODO: Set SessionIdManager name.
            // The env variable JETTY_WORKER_INSTANCE must be set.
            //


            SessionHandler sessionHandler = context.getSessionHandler();

            // Create the mongo session data store based on the configured
            // properties.
            final MongoSessionDataStoreFactory dataStoreFactory = new MongoSessionDataStoreFactory();
            dataStoreFactory.setHost(host);
            dataStoreFactory.setDbName(dbname);
            dataStoreFactory.setCollectionName(collectionName);
            log.debug("Using default collection name: " + dataStoreFactory.getCollectionName());
            final SessionDataStore dataStore = dataStoreFactory.getSessionDataStore(sessionHandler);


            // Create the session cache and add the data store to it.
            final NullSessionCache sessionCache = new NullSessionCache(sessionHandler);
            sessionCache.setSessionDataStore(dataStore);

            // Set the mongo configuration properties.
            // jetty.session.mongo.host
            // jetty.session.mongo.dbName
        }
    }
}