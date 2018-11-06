package com.rob.jetty.session.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Root is a spring MVC controller that handles requests to the root of the
 * dispatcher servlet context mapping.
 *
 * @author Rob Benton
 */
@Controller
@RequestMapping(Root.PATH)
public class Root
{
    public static final String PATH = "/";

    private static final Logger log = LoggerFactory.getLogger(Root.class);

    private static final String VIEWNAME = "index";

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView get(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            session = request.getSession(true);
            session.setAttribute("test", "data");
            log.debug("New session created with id: " + session.getId());
        } else
        {
            log.debug("Session exists: " + session.getId());
            log.debug("test attribute = " + session.getAttribute("test"));
        }

        return new ModelAndView(VIEWNAME);
    }
}
