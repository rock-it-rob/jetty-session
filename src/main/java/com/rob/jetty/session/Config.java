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
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Config is the main configuration class for the application.
 *
 * @author Rob Benton
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class Config
{
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public static void main(String[] args)
    {
        SpringApplication.run(Config.class, args);
    }

    @Value("${mongo.host}")
    private String mongoHost;

    @Value("${mongo.port}")
    private Integer mongoPort;

    @Value("${mongo.dbname}")
    private String mongoDbname;

    @Value("${mongo.collection.name}")
    private String mongoCollectionName;

    @Bean
    public ServletWebServerFactory servletWebServerFactory()
    {
        assert (mongoHost != null);
        assert (mongoPort != null);
        assert (mongoDbname != null);
        assert (mongoCollectionName != null);

        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addConfigurations(
            new MongoSessionDataStoreConfig(mongoHost, mongoPort, mongoDbname, mongoCollectionName)
        );
        return factory;
    }


    static final class MongoSessionDataStoreConfig extends AbstractConfiguration
    {
        private final String host;
        private final int port;
        private final String dbname;
        private final String collectionName;

        /**
         * Creates a new instance.
         *
         * @param host           hostname of the mongodb server
         * @param port           port on the host where mongodb is listening
         * @param dbname         name of the database
         * @param collectionName name of the collection to use
         */
        MongoSessionDataStoreConfig(String host, int port, String dbname, String collectionName)
        {
            this.host = host;
            this.port = port;
            this.dbname = dbname;
            this.collectionName = collectionName;
        }

        @Override
        public void configure(WebAppContext context) throws Exception
        {
            log.debug(
                String.format(
                    "Utilizing MongoDb for session data storage. Host[%s] Port[%d] dbName[%s] CollectionName[%s]",
                    host, port, dbname, collectionName
                )
            );

            //
            // TODO: Set SessionIdManager name.
            // The env variable JETTY_WORKER_INSTANCE must be set.
            //


            SessionHandler sessionHandler = context.getSessionHandler();

            // Create the mongo session data store based on the configured
            // properties.
            final MongoSessionDataStoreFactory dataStoreFactory = new MongoSessionDataStoreFactory();
            dataStoreFactory.setHost(host);
            dataStoreFactory.setPort(port);
            dataStoreFactory.setDbName(dbname);
            dataStoreFactory.setCollectionName(collectionName);
            final SessionDataStore dataStore = dataStoreFactory.getSessionDataStore(sessionHandler);

            // Create the session cache and add the data store to it.
            final NullSessionCache sessionCache = new NullSessionCache(sessionHandler);
            sessionCache.setSessionDataStore(dataStore);
        }
    }
}