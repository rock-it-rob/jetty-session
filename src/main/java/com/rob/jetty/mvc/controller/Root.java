package com.rob.jetty.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        return new ModelAndView(VIEWNAME);
    }
}
