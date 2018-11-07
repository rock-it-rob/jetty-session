package com.rob.jetty.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * Config is the main configuration class for the application.
 *
 * @author Rob Benton
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class Config
{
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public static void main(String[] args)
    {
        SpringApplication.run(Config.class, args);
    }
}