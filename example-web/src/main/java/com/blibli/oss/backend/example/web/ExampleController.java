package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.command.model.example.GetExampleCommandRequest;
import com.blibli.oss.backend.example.command.example.GetExampleCommand;
import com.blibli.oss.backend.example.web.model.response.example.GetExampleWebResponse;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
public class ExampleController implements InitializingBean {

  @Autowired
  private SchedulerHelper schedulerHelper;

  @Autowired
  private CommandExecutor commandExecutor;

  private Scheduler schedulerCommand;

  @Override
  public void afterPropertiesSet() throws Exception {
    schedulerCommand = schedulerHelper.of("COMMAND");
  }

  @GetMapping(value = "/example", produces = MediaType.TEXT_HTML_VALUE)
  public Mono<String> home() {
    return commandExecutor.execute(GetExampleCommand.class, GetExampleCommandRequest.getInstance())
      .map(GetExampleWebResponse::getResponse)
      .subscribeOn(schedulerCommand);
  }
}
