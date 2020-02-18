package com.blibli.oss.backend.example.command.impl.customer;

import com.blibli.oss.backend.command.model.customer.CreateCustomerCommandRequest;
import com.blibli.oss.backend.example.command.customer.CreateCustomerCommand;
import com.blibli.oss.backend.example.entity.Customer;
import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.streaming.repository.CustomerKafkaRepository;
import com.blibli.oss.backend.example.web.model.response.customer.CreateCustomerWebResponse;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.UUID;

@Service
public class CreateCustomerCommandImpl implements CreateCustomerCommand {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerKafkaRepository customerKafkaRepository;

  @Autowired
  private SchedulerHelper schedulerHelper;

  @Override
  public Mono<CreateCustomerWebResponse> execute(CreateCustomerCommandRequest request) {
    return Mono.fromCallable(() -> convertToCustomer(request))
      .flatMap(customer -> customerRepository.save(customer))
      .map(this::convertToWebResponse);
  }

  private Customer convertToCustomer(CreateCustomerCommandRequest request) {
    Customer customer = Customer.builder()
      .id(UUID.randomUUID().toString())
      .build();
    BeanUtils.copyProperties(request, customer);
    return customer;
  }

  private CreateCustomerWebResponse convertToWebResponse(Customer customer) {
    CreateCustomerWebResponse webResponse = new CreateCustomerWebResponse();
    BeanUtils.copyProperties(customer, webResponse);
    return webResponse;
  }
}
