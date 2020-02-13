package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.command.model.customer.CreateCustomerCommandRequest;
import com.blibli.oss.backend.command.model.customer.UpdateCustomerCommandRequest;
import com.blibli.oss.backend.common.helper.ResponseHelper;
import com.blibli.oss.backend.common.model.response.Response;
import com.blibli.oss.backend.example.command.customer.CreateCustomerCommand;
import com.blibli.oss.backend.example.command.customer.UpdateCustomerCommand;
import com.blibli.oss.backend.example.web.model.request.customer.CreateCustomerWebRequest;
import com.blibli.oss.backend.example.web.model.request.customer.UpdateCustomerWebRequest;
import com.blibli.oss.backend.example.web.model.response.customer.CreateCustomerWebResponse;
import com.blibli.oss.backend.example.web.model.response.customer.UpdateCustomerWebResponse;
import com.blibli.oss.backend.mandatoryparameter.model.MandatoryParameter;
import com.blibli.oss.backend.mandatoryparameter.swagger.annotation.MandatoryParameterAtHeader;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

  @Autowired
  private CommandExecutor commandExecutor;

  @Autowired
  private SchedulerHelper schedulerHelper;

  @MandatoryParameterAtHeader
  @PostMapping(
    value = "/api/customers",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Response<CreateCustomerWebResponse>> create(MandatoryParameter mandatoryParameter,
                                                          @RequestBody CreateCustomerWebRequest request) {
    return commandExecutor.execute(CreateCustomerCommand.class, toCreateCustomerCommandRequest(request))
      .map(ResponseHelper::ok)
      .subscribeOn(schedulerHelper.of("COMMAND"));
  }

  private CreateCustomerCommandRequest toCreateCustomerCommandRequest(CreateCustomerWebRequest request) {
    CreateCustomerCommandRequest commandRequest = new CreateCustomerCommandRequest();
    BeanUtils.copyProperties(request, commandRequest);
    return commandRequest;
  }

  @MandatoryParameterAtHeader
  @PutMapping(
    value = "/api/customers/{customerId}",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Response<UpdateCustomerWebResponse>> create(MandatoryParameter mandatoryParameter,
                                                          @PathVariable("customerId") String customerId,
                                                          @RequestBody UpdateCustomerWebRequest request) {
    return commandExecutor.execute(UpdateCustomerCommand.class, toUpdateCustomerWebRequest(customerId, request))
      .map(ResponseHelper::ok)
      .subscribeOn(schedulerHelper.of("COMMAND"));
  }

  private UpdateCustomerCommandRequest toUpdateCustomerWebRequest(String customerId, UpdateCustomerWebRequest request) {
    UpdateCustomerCommandRequest commandRequest = new UpdateCustomerCommandRequest();
    commandRequest.setId(customerId);
    BeanUtils.copyProperties(request, commandRequest);
    return commandRequest;
  }
}
