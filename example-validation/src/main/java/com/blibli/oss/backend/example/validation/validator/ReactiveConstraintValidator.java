package com.blibli.oss.backend.example.validation.validator;

import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * IN THE FUTURE, THIS CLASS WILL BE MOVED TO VALIDATION-MODULE
 */
public abstract class ReactiveConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

  protected A constraintAnnotation;

  @Override
  public void initialize(A constraintAnnotation) {
    this.constraintAnnotation = constraintAnnotation;
  }

  @Override
  public final boolean isValid(T value, ConstraintValidatorContext context) {
    return isValid(Mono.justOrEmpty(value), constraintAnnotation, context).block();
  }

  public abstract Mono<Boolean> isValid(Mono<T> value, A constraintAnnotation, ConstraintValidatorContext context);
}
