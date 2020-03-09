package com.blibli.oss.backend.example.validation.validator;

import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.validation.CustomerMustExists;
import com.blibli.oss.backend.validation.AbstractReactiveConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidatorContext;

public class CustomerMustExistsValidator extends AbstractReactiveConstraintValidator<CustomerMustExists, String> {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<Boolean> validate(String value, CustomerMustExists annotation, ConstraintValidatorContext context) {
    return customerRepository.existsById(value);
  }
}
