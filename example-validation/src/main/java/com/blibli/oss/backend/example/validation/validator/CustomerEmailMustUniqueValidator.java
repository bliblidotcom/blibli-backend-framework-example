package com.blibli.oss.backend.example.validation.validator;

import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.validation.CustomerEmailMustUnique;
import com.blibli.oss.backend.validation.AbstractReactiveConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidatorContext;

public class CustomerEmailMustUniqueValidator extends AbstractReactiveConstraintValidator<CustomerEmailMustUnique, String> {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<Boolean> validate(String value, CustomerEmailMustUnique annotation, ConstraintValidatorContext context) {
    return customerRepository.countByEmail(value)
      .map(count -> count == 0);
  }
}
