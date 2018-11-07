package com.rob.jetty.session.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Creates a new {@link javax.servlet.http.HttpSession}. If a current
     * session exists it is invalidated.
     *
     * @param request HttpServletRequest
     * @return OK
     */
    @PostMapping
    public HttpStatus post(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            log.debug("Invalidating session.");
            session.invalidate();
        }
        session = request.getSession(true);
        log.debug("Created new session: " + session.getId());
        return HttpStatus.OK;
    }

    /**
     * Deletes the current session.
     *
     * @param request HttpServletRequest
     * @return OK
     */
    @DeleteMapping
    public HttpStatus delete(HttpServletRequest request)
    {
        final HttpSession session = request.getSession(false);
        if (session != null)
        {
            log.debug("Invalidating session.");
            session.invalidate();
        }
        return HttpStatus.OK;
    }
}
