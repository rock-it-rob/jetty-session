package com.rob.jetty.session.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Default defines the spring beans and configuration for the default spring
 * profile, {@link #PROFILE}.
 *
 * @author Rob Benton
 */
@Profile(Default.PROFILE)
@Configuration
public class Default
{
    public static final String PROFILE = "default";
}