package com.champcoder.common.dto;

import com.champcoder.common.handlers.ScheduleJobRequestHandler;
import org.jobrunr.jobs.lambdas.JobRequest;

/**
 * Model class use to accept Schedule Job Request.
 *
 * @param jobId               Job ID (If we want to set custom Job ID)
 * @param scheduleForInMillis If we want to schedule a Job for some time in milliseconds.
 */
public record ScheduleJobRequestDto(String jobId, Long scheduleForInMillis)
    implements JobRequest {

  @Override
  public Class<ScheduleJobRequestHandler> getJobRequestHandler() {
    return ScheduleJobRequestHandler.class;
  }
}
