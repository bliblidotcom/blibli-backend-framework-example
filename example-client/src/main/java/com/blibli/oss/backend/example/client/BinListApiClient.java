package com.blibli.oss.backend.example.client;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.blibli.oss.backend.example.client.customizer.BinListWebClientCustomizer;
import com.blibli.oss.backend.example.client.error.BinListErrorResolver;
import com.blibli.oss.backend.example.client.fallback.BinListApiClientFallback;
import com.blibli.oss.backend.example.client.model.BinResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

@ApiClient(
  name = "binListApiClient",
  fallback = BinListApiClientFallback.class,
  webClientCustomizers = {
    BinListWebClientCustomizer.class
  },
  errorResolver = BinListErrorResolver.class
)
public interface BinListApiClient {

  @RequestMapping(
    value = "/{number}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  Mono<BinResponse> lookup(@PathVariable("number") String number);

}
