package com.blibli.oss.backend.example.validation.validator;

import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.validation.CustomerEmailMustUnique;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidatorContext;

public class CustomerEmailMustUniqueValidator extends ReactiveConstraintValidator<CustomerEmailMustUnique, String> {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<Boolean> isValid(Mono<String> value, CustomerEmailMustUnique constraintAnnotation, ConstraintValidatorContext context) {
    return value.flatMap(email -> customerRepository.countByEmail(email))
      .map(count -> count == 0)
      .switchIfEmpty(Mono.just(true));
  }
}
