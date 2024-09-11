package com.champcoder.leader.controllers;

import com.champcoder.common.dto.ResponseMessage;
import com.champcoder.common.dto.ScheduleJobRequestDto;
import com.champcoder.common.dto.ScheduleJobResponseDto;
import com.champcoder.leader.services.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/job")
public class SchedulerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);
  private final SchedulerService schedulerService;

  public SchedulerController(SchedulerService schedulerService) {
    this.schedulerService = schedulerService;
  }

  @PostMapping("/schedule")
  public ResponseEntity<ScheduleJobResponseDto> scheduleJob(
      @RequestBody ScheduleJobRequestDto scheduleJobRequestDto) {
    LOGGER.info("Schedule Job received request : {}", scheduleJobRequestDto);
    ScheduleJobResponseDto scheduleJobResponseDto =
        schedulerService.scheduleJob(scheduleJobRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(scheduleJobResponseDto);
  }

  @DeleteMapping("/delete/{jobId}")
  public ResponseEntity<ResponseMessage> deleteJob(@PathVariable("jobId") String jobId) {
    LOGGER.info("Delete Job received request with JobId : {}", jobId);
    ResponseMessage responseMessage;
    responseMessage = schedulerService.deleteJob(jobId);
    return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
  }
}
