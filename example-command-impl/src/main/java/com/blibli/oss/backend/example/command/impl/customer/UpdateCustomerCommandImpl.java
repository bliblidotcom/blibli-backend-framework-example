package com.blibli.oss.backend.example.command.impl.customer;

import com.blibli.oss.backend.command.model.customer.UpdateCustomerCommandRequest;
import com.blibli.oss.backend.example.command.customer.UpdateCustomerCommand;
import com.blibli.oss.backend.example.entity.Customer;
import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.streaming.repository.CustomerKafkaRepository;
import com.blibli.oss.backend.example.web.model.response.customer.UpdateCustomerWebResponse;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateCustomerCommandImpl implements UpdateCustomerCommand {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerKafkaRepository customerKafkaRepository;

  @Autowired
  private SchedulerHelper schedulerHelper;

  @Override
  public Mono<UpdateCustomerWebResponse> execute(UpdateCustomerCommandRequest request) {
    return customerRepository.findById(request.getId())
      .doOnNext(customer -> BeanUtils.copyProperties(request, customer))
      .flatMap(customer -> customerRepository.save(customer))
      .map(this::convertToWebResponse);
  }

  private UpdateCustomerWebResponse convertToWebResponse(Customer customer) {
    UpdateCustomerWebResponse webResponse = new UpdateCustomerWebResponse();
    BeanUtils.copyProperties(customer, webResponse);
    return webResponse;
  }
}
