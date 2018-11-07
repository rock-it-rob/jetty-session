package com.rob.jetty.session.configuration;

import org.eclipse.jetty.nosql.mongodb.MongoSessionDataStoreFactory;
import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.SessionDataStore;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MongoSessionJettyConfiguration is a Jetty {@link org.eclipse.jetty.webapp.Configuration}
 * for setting the session data store to use a MongoDB instance. The
 * {@link NullSessionCache} is used for the session cache implementation.
 *
 * <h1>Configuring SessionIdManager</h1>
 * It is <strong>crucial</strong> when using multiple instances of Jetty to
 * set the SessionIdManager worker name to a unique value. See the documentation
 * here:
 * <p>
 * https://www.eclipse.org/jetty/documentation/current/session-configuration-housekeeper.html
 *
 * @author Rob Benton
 */
public class MongoSessionJettyConfiguration extends AbstractConfiguration
{
    private static final Logger log = LoggerFactory.getLogger(MongoSessionJettyConfiguration.class);

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
    public MongoSessionJettyConfiguration(String host, int port, String dbname, String collectionName)
    {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.collectionName = collectionName;
    }

    /**
     * Sets the {@link WebAppContext} to use the following implementations:
     * <ul>
     * <li>SessionCache: {@link NullSessionCache}</li>
     * <li>SessionDataStore: {@link org.eclipse.jetty.nosql.mongodb.MongoSessionDataStore}</li>
     * </ul>
     * <p>
     * Any errors encountered during this process are fatal.
     *
     * @param context WebAppContext
     * @throws RuntimeException for any errors.
     */
    @Override
    public void configure(WebAppContext context)
    {
        log.debug(
            String.format(
                "Utilizing MongoDb for session data storage. Host[%s] Port[%d] dbName[%s] CollectionName[%s]",
                host, port, dbname, collectionName
            )
        );

        try
        {
            final SessionHandler sessionHandler = context.getSessionHandler();

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

            // Once the session cache has been created it must be explicitly
            // set back on the handler.
            sessionHandler.setSessionCache(sessionCache);
        } catch (Exception e)
        {
            log.error("Error configuring Jetty session: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
