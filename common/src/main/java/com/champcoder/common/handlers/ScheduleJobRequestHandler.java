package com.champcoder.common.handlers;

import com.champcoder.common.dto.ScheduleJobRequestDto;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

public record ScheduleJobRequestHandler() implements JobRequestHandler<ScheduleJobRequestDto> {
  // Create the bean of each handler classes in all the worker nodes.
  @Override
  public void run(ScheduleJobRequestDto scheduleJobRequestDto) throws Exception {
    // Here we define the Scheduler logic (what to perform using the request).

  }
}
