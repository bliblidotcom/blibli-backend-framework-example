package com.blibli.oss.backend.example.client;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.blibli.oss.backend.example.client.customizer.ExampleCodecCustomizer;
import com.blibli.oss.backend.example.client.fallback.ExampleApiClientFallback;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@ApiClient(
  name = "exampleApiClient",
  fallback = ExampleApiClientFallback.class,
  codecCustomizers = {
    ExampleCodecCustomizer.class
  }
)
public interface ExampleApiClient {

  @RequestMapping("/")
  Mono<String> home();

}
