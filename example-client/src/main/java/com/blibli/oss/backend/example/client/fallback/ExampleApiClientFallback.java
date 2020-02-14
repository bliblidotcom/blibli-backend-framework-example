package com.blibli.oss.backend.example.client.fallback;

import com.blibli.oss.backend.example.client.ExampleApiClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ExampleApiClientFallback implements ExampleApiClient {

  @Override
  public Mono<String> home() {
    return Mono.just("Ups");
  }
}
