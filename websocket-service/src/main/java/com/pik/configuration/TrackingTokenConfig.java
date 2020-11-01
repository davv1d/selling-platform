package com.pik.configuration;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrackingTokenConfig {
    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.registerTrackingEventProcessorConfiguration(configuration ->
                TrackingEventProcessorConfiguration.forSingleThreadedProcessing()
                        .andInitialTrackingToken(StreamableMessageSource::createHeadToken));
    }
}
