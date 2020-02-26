package com.blibli.oss.backend.example.client.error;

import com.blibli.oss.backend.apiclient.error.ApiErrorResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

@Slf4j
@Component
public class BinListErrorResolver implements ApiErrorResolver {

  @Override
  public Mono<Object> resolve(Throwable throwable, Class<?> type, Method method, Object[] arguments) {
    log.error(String.format("Ups error call ApiClient %s.%s", type, method), throwable);
    return Mono.empty(); // continue to fallback
  }
}
