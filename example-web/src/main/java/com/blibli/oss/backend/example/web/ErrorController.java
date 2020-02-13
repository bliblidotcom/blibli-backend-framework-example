package com.blibli.oss.backend.example.web;

import com.blibli.oss.backend.command.controller.CommandErrorController;
import com.blibli.oss.backend.common.webflux.controller.CommonErrorController;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController implements CommonErrorController, CommandErrorController, MessageSourceAware {

  @Getter
  @Setter
  private MessageSource messageSource;

  @Override
  public Logger getLogger() {
    return log;
  }
}
