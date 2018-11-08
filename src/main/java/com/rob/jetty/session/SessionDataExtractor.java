package com.rob.jetty.session;

/**
 * SessionDataExtractor defines the interface for retrieving the bulk session
 * data from a session.
 *
 * @author Rob Benton
 */
public interface SessionDataExtractor
{
    /**
     * Extracts the session contents corresponding to the session id.
     *
     * @param sessionId HttpSession id
     * @return SessionContents of the session matching the id or null if no
     * session exists for the given id.
     */
    SessionContents extract(String sessionId);
}
