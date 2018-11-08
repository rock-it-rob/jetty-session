package com.rob.jetty.session;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.eclipse.jetty.nosql.mongodb.MongoSessionDataStore;
import org.eclipse.jetty.server.session.Session;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * MongoSessionDataExtractor retrieves the session contents from an HttpSession
 * stored in a MongoDB database through a {@link org.eclipse.jetty.nosql.mongodb.MongoSessionDataStore}.
 *
 * @author Rob Benton
 */
public class MongoSessionDataExtractor implements SessionDataExtractor
{
    private static final Logger log = LoggerFactory.getLogger(MongoSessionDataExtractor.class);

    /**
     * Default constructor.
     */
    public MongoSessionDataExtractor()
    {
    }

    /**
     * Gets the {@link SessionContents} for the given session Id.
     *
     * @param sessionId HttpSession id
     * @return SessionContents or null if no session was found for the id.
     */
    @Override
    public SessionContents extract(String sessionId)
    {
        WebAppContext context = WebAppContext.getCurrentWebAppContext();
        final SessionHandler sessionHandler = context.getSessionHandler();
        Session session = sessionHandler.getSession(sessionId);

        if (session == null)
        {
            return null;
        }

        final SessionContents sessionContents = new SessionContents();

        // Extract all the attributes.
        final HashMap<String, Object> attributes = new HashMap<>();
        for (String a : session.getNames())
        {
            attributes.put(a, session.getAttribute(a));
        }
        sessionContents.setAttributes(attributes);

        // Extract the raw session data.
        final MongoSessionDataStore dataStore = (MongoSessionDataStore) sessionHandler.getSessionCache().getSessionDataStore();
        final DBCollection dbCollection = dataStore.getDBCollection();
        final DBObject dbObject = dbCollection.findOne(new BasicDBObject("id", sessionId));
        if (dbObject != null)
        {
            sessionContents.setContents(dbObject);
        } else
        {
            log.warn("Could not locate DBObject for session.");
        }

        return sessionContents;
    }
}
