package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.mandatoryparameter.model.MandatoryParameter;
import com.blibli.oss.backend.mandatoryparameter.swagger.annotation.MandatoryParameterAtHeader;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  @Autowired
  private SchedulerHelper schedulerHelper;

  @MandatoryParameterAtHeader
  @GetMapping(value = "/hello/mandatory-parameter", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<MandatoryParameter> mandatoryParameter(MandatoryParameter mandatoryParameter) {
    return Mono.fromCallable(() -> mandatoryParameter)
      .subscribeOn(schedulerHelper.of("HELLO"));
  }

}
