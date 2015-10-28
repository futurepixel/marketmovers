package io.marketmovers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

/**
 * Created by mikechabot on 10/28/15.
 */
@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = Logger.getLogger(AppListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Starting marketmovers...");
    }

}
