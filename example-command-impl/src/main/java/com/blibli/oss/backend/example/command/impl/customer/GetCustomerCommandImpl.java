package com.blibli.oss.backend.example.command.impl.customer;

import com.blibli.oss.backend.command.model.customer.GetCustomerCommandRequest;
import com.blibli.oss.backend.example.command.customer.GetCustomerCommand;
import com.blibli.oss.backend.example.entity.Customer;
import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.web.model.response.customer.GetCustomerWebResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetCustomerCommandImpl implements GetCustomerCommand {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<GetCustomerWebResponse> execute(GetCustomerCommandRequest request) {
    return customerRepository.findById(request.getId())
      .map(this::toWebResponse);
  }

  private GetCustomerWebResponse toWebResponse(Customer customer) {
    GetCustomerWebResponse response = new GetCustomerWebResponse();
    BeanUtils.copyProperties(customer, response);
    return response;
  }
}
