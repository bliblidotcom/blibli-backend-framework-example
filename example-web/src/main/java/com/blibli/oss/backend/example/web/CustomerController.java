package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.command.model.customer.CreateCustomerCommandRequest;
import com.blibli.oss.backend.command.model.customer.GetCustomerCommandRequest;
import com.blibli.oss.backend.command.model.customer.ListCustomerCommandRequest;
import com.blibli.oss.backend.command.model.customer.UpdateCustomerCommandRequest;
import com.blibli.oss.backend.common.helper.PagingHelper;
import com.blibli.oss.backend.common.helper.ResponseHelper;
import com.blibli.oss.backend.common.model.request.PagingRequest;
import com.blibli.oss.backend.common.model.request.SortBy;
import com.blibli.oss.backend.common.model.response.Response;
import com.blibli.oss.backend.common.swagger.annotation.PagingRequestInQuery;
import com.blibli.oss.backend.example.command.customer.CreateCustomerCommand;
import com.blibli.oss.backend.example.command.customer.GetCustomerCommand;
import com.blibli.oss.backend.example.command.customer.ListCustomerCommand;
import com.blibli.oss.backend.example.command.customer.UpdateCustomerCommand;
import com.blibli.oss.backend.example.web.model.request.customer.CreateCustomerWebRequest;
import com.blibli.oss.backend.example.web.model.request.customer.UpdateCustomerWebRequest;
import com.blibli.oss.backend.example.web.model.response.customer.CreateCustomerWebResponse;
import com.blibli.oss.backend.example.web.model.response.customer.GetCustomerWebResponse;
import com.blibli.oss.backend.example.web.model.response.customer.UpdateCustomerWebResponse;
import com.blibli.oss.backend.mandatoryparameter.model.MandatoryParameter;
import com.blibli.oss.backend.mandatoryparameter.swagger.annotation.MandatoryParameterAtHeader;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CustomerController implements InitializingBean {

  @Autowired
  private CommandExecutor commandExecutor;

  @Autowired
  private SchedulerHelper schedulerHelper;

  private Scheduler commandScheduler;

  @Override
  public void afterPropertiesSet() throws Exception {
    commandScheduler = schedulerHelper.of("COMMAND");
  }

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
      .subscribeOn(commandScheduler);
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
  public Mono<Response<UpdateCustomerWebResponse>> update(MandatoryParameter mandatoryParameter,
                                                          @PathVariable("customerId") String customerId,
                                                          @RequestBody UpdateCustomerWebRequest request) {
    return commandExecutor.execute(UpdateCustomerCommand.class, toUpdateCustomerWebRequest(customerId, request))
      .map(ResponseHelper::ok)
      .subscribeOn(commandScheduler);
  }

  private UpdateCustomerCommandRequest toUpdateCustomerWebRequest(String customerId, UpdateCustomerWebRequest request) {
    UpdateCustomerCommandRequest commandRequest = new UpdateCustomerCommandRequest();
    commandRequest.setId(customerId);
    BeanUtils.copyProperties(request, commandRequest);
    return commandRequest;
  }

  @MandatoryParameterAtHeader
  @GetMapping(
    value = "/api/customers/{customerId}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Response<GetCustomerWebResponse>> get(MandatoryParameter mandatoryParameter,
                                                    @PathVariable("customerId") String customerId) {
    return commandExecutor.execute(GetCustomerCommand.class, toGetCustomerCommandRequest(customerId))
      .map(ResponseHelper::ok)
      .subscribeOn(commandScheduler);
  }

  private GetCustomerCommandRequest toGetCustomerCommandRequest(String customerId) {
    return GetCustomerCommandRequest.builder().id(customerId).build();
  }

  @MandatoryParameterAtHeader
  @PagingRequestInQuery
  @GetMapping(
    value = "/api/customers",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Response<List<GetCustomerWebResponse>>> list(MandatoryParameter mandatoryParameter,
                                                           PagingRequest pagingRequest) {
    return commandExecutor.execute(ListCustomerCommand.class, toListCustomerCommandRequest(pagingRequest))
      .map(response -> ResponseHelper.ok(response.getCustomers(), PagingHelper.toPaging(pagingRequest, response.getTotal())))
      .subscribeOn(commandScheduler);
  }

  private ListCustomerCommandRequest toListCustomerCommandRequest(PagingRequest pagingRequest) {
    Map<String, String> map = pagingRequest.getSortBy().stream().
      collect(Collectors.toMap(SortBy::getPropertyName, sortBy -> sortBy.getDirection().name()));

    return ListCustomerCommandRequest.builder()
      .page(pagingRequest.getPage())
      .size(pagingRequest.getItemPerPage())
      .orders(map)
      .build();
  }
}
