package com.blibli.oss.backend.example.validation.validator;

import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.validation.CustomerMustExists;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidatorContext;

public class CustomerMustExistsValidator extends ReactiveConstraintValidator<CustomerMustExists, String> {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<Boolean> isValid(Mono<String> value, CustomerMustExists constraintAnnotation, ConstraintValidatorContext context) {
    return value.flatMap(customerId -> customerRepository.existsById(customerId))
      .switchIfEmpty(Mono.just(true));
  }
}
