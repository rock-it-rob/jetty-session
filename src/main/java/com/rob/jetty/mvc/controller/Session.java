package com.rob.jetty.mvc.controller;

import com.rob.jetty.session.SessionContents;
import com.rob.jetty.session.SessionDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session is a spring MVC controller that handles the creation and destruction
 * of {@link javax.servlet.http.HttpSession}s.
 *
 * @author Rob Benton
 */
@RestController
@RequestMapping(Session.PATH)
public class Session
{
    public static final String PATH = "session";

    private static final Logger log = LoggerFactory.getLogger(Session.class);

    @Autowired
    private SessionDataExtractor sessionDataExtractor;

    /**
     * Creates a new {@link javax.servlet.http.HttpSession}. If a current
     * session exists it is invalidated.
     *
     * @param request HttpServletRequest
     * @return OK
     */
    @PostMapping
    public ResponseEntity post(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            log.debug("Invalidating session.");
            session.invalidate();
        }
        session = request.getSession(true);
        log.debug("Created new session: " + session.getId());

        return ResponseEntity.ok().build();
    }

    /**
     * Deletes the current session.
     *
     * @param request HttpServletRequest
     * @return OK
     */
    @DeleteMapping
    public ResponseEntity delete(HttpServletRequest request)
    {
        final HttpSession session = request.getSession(false);

        if (session == null)
        {
            return ResponseEntity.notFound().build();
        }

        log.debug("Invalidating session.");
        session.invalidate();

        return ResponseEntity.ok().build();
    }

    /**
     * Gets the contents of the session.
     *
     * @param request HttpServletRequest
     * @return a ResponseEntity containing the {@link SessionContents}
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionContents> get(HttpServletRequest request)
    {
        final HttpSession session = request.getSession(false);

        if (session == null)
        {
            log.debug("No HttpSession");
            return ResponseEntity.notFound().build();
        }

        final SessionContents sessionContents = sessionDataExtractor.extract(session.getId());

        if (sessionContents == null)
        {
            log.debug("No SessionContents");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sessionContents);
    }
}
