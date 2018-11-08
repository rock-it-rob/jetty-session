package com.rob.jetty.profile;

import com.rob.jetty.session.DefaultSessionDataExtractor;
import com.rob.jetty.session.SessionDataExtractor;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public SessionDataExtractor sessionDataExtractor()
    {
        return new DefaultSessionDataExtractor();
    }
}
