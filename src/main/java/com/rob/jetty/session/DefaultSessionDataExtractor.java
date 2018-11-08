package com.rob.jetty.session;

import org.eclipse.jetty.server.session.Session;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.HashMap;

/**
 * DefaultSessionDataExtractor is the implementation of {@link SessionDataExtractor}
 * used when no specific session data store implementation is chosen for the
 * embedded Jetty server.
 *
 * @author Rob Benton
 */
public class DefaultSessionDataExtractor implements SessionDataExtractor
{
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
        sessionContents.setContents(session.toString());

        return sessionContents;
    }
}
