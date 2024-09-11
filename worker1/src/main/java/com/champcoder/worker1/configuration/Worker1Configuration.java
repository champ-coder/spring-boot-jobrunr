package com.champcoder.worker1.configuration;

import com.champcoder.common.handlers.ScheduleJobRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Worker1Configuration {

  @Bean
  public ScheduleJobRequestHandler jobRequestHandler() {
    return new ScheduleJobRequestHandler();
  }
}
