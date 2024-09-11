package com.champcoder.leader.services;

import com.champcoder.common.dto.ResponseMessage;
import com.champcoder.common.dto.ScheduleJobRequestDto;
import com.champcoder.common.dto.ScheduleJobResponseDto;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.storage.JobNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SchedulerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);
  private final JobRequestScheduler jobRequestScheduler;

  public SchedulerService(JobRequestScheduler jobRequestScheduler) {
    this.jobRequestScheduler = jobRequestScheduler;
  }

  public ScheduleJobResponseDto scheduleJob(ScheduleJobRequestDto scheduleJobRequestDto) {
    scheduleJobRequestDto = Optional.ofNullable(scheduleJobRequestDto)
        .orElseThrow(() -> new RuntimeException("Scheduler Request can't ne null"));
    String scheduledJobId = "";
    // If we want to schedule a Job which will execute after some time in milliseconds.
    if (scheduleJobRequestDto.scheduleForInMillis() != null) {
      // If jobId is present in the request
      if (StringUtils.hasText(scheduleJobRequestDto.jobId())) {
        scheduledJobId =
            jobRequestScheduler.schedule(UUID.fromString(scheduleJobRequestDto.jobId()),
                Instant.now().plusMillis(scheduleJobRequestDto.scheduleForInMillis()),
                scheduleJobRequestDto).toString();
      } else {
        scheduledJobId = jobRequestScheduler.schedule(
            Instant.now().plusMillis(scheduleJobRequestDto.scheduleForInMillis()),
            scheduleJobRequestDto).toString();
      }
    }
    LOGGER.info("Scheduled Job's JobID: {} and ScheduledAt: {} for ScheduledFor: {} milliseconds",
        scheduledJobId, Instant.now(), scheduleJobRequestDto.scheduleForInMillis());
    return new ScheduleJobResponseDto(scheduledJobId, scheduleJobRequestDto.scheduleForInMillis());
  }

  public ResponseMessage deleteJob(String jobId) {
    try {
      jobRequestScheduler.delete(UUID.fromString(jobId));
    } catch (Exception e) {
      // If the jobId not Found then throw JobNotFoundException
      if (e instanceof JobNotFoundException jobNotFoundException) {
        return new ResponseMessage(jobNotFoundException.getMessage());
      }
    }
    LOGGER.info("Job with id {} Deleted.", jobId);
    return new ResponseMessage(String.format("Job with id %s Deleted.", jobId));
  }
}
