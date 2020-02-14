package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.command.model.binlist.GetBinListCommandRequest;
import com.blibli.oss.backend.common.helper.ResponseHelper;
import com.blibli.oss.backend.common.model.response.Response;
import com.blibli.oss.backend.example.command.binlist.GetBinListCommand;
import com.blibli.oss.backend.example.web.model.response.binlist.GetBinListWebResponse;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
public class BinListController implements InitializingBean {

  @Autowired
  private CommandExecutor commandExecutor;

  @Autowired
  private SchedulerHelper schedulerHelper;

  private Scheduler schedulerCommand;

  @Override
  public void afterPropertiesSet() throws Exception {
    schedulerCommand = schedulerHelper.of("COMMAND");
  }

  @GetMapping(
    value = "/binlist/{number}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Response<GetBinListWebResponse>> lookup(@PathVariable("number") String number) {
    return commandExecutor.execute(GetBinListCommand.class, toGetBinListCommandRequest(number))
      .map(ResponseHelper::ok)
      .subscribeOn(schedulerCommand);
  }

  private GetBinListCommandRequest toGetBinListCommandRequest(@PathVariable("number") String number) {
    return GetBinListCommandRequest.builder().number(number).build();
  }
}
