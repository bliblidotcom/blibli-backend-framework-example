package com.blibli.oss.backend.example.command.impl.customer;

import com.blibli.oss.backend.command.model.customer.ListCustomerCommandRequest;
import com.blibli.oss.backend.example.command.customer.ListCustomerCommand;
import com.blibli.oss.backend.example.entity.Customer;
import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.web.model.response.customer.GetCustomerWebResponse;
import com.blibli.oss.backend.example.web.model.response.customer.ListCustomerWebResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCustomerCommandImpl implements ListCustomerCommand {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<ListCustomerWebResponse> execute(ListCustomerCommandRequest request) {
    return Mono.fromCallable(() -> toSort(request))
      .flatMapMany(sort -> customerRepository.findAll(sort))
      .map(this::toGetCustomerWebResponse)
      .collectList()
      .map(this::toWebResponse)
      .flatMap(this::fillTotal);
  }

  private Mono<ListCustomerWebResponse> fillTotal(ListCustomerWebResponse response) {
    return customerRepository.count()
      .map(aLong -> {
        response.setTotal(aLong.intValue());
        return response;
      });
  }

  private ListCustomerWebResponse toWebResponse(List<GetCustomerWebResponse> customers) {
    return ListCustomerWebResponse.builder()
      .customers(customers)
      .build();
  }

  private GetCustomerWebResponse toGetCustomerWebResponse(Customer customer) {
    GetCustomerWebResponse response = new GetCustomerWebResponse();
    BeanUtils.copyProperties(customer, response);
    return response;
  }

  private Sort toSort(ListCustomerCommandRequest request) {
    List<Sort.Order> orders = request.getOrders().entrySet().stream().map(entry -> {
      if ("DESC".equals(entry.getValue())) {
        return Sort.Order.desc(entry.getKey());
      } else {
        return Sort.Order.asc(entry.getKey());
      }
    }).collect(Collectors.toList());

    return Sort.by(orders);
  }
}
