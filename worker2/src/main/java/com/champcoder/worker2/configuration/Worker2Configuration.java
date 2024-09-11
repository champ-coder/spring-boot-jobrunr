package com.champcoder.worker2.configuration;

import com.champcoder.common.handlers.ScheduleJobRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Worker2Configuration {

  @Bean
  public ScheduleJobRequestHandler jobRequestHandler() {
    return new ScheduleJobRequestHandler();
  }
}
