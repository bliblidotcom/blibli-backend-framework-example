package com.blibli.oss.backend.example.validation;

import com.blibli.oss.backend.example.validation.validator.CustomerMustExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CustomerMustExistsValidator.class})
public @interface CustomerMustExists {

  String message() default "MustExists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] path() default {};
}
