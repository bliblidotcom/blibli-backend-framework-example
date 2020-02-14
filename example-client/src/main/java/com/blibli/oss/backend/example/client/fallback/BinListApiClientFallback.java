package com.blibli.oss.backend.example.client.fallback;

import com.blibli.oss.backend.example.client.BinListApiClient;
import com.blibli.oss.backend.example.client.model.BinResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BinListApiClientFallback implements BinListApiClient {

  @Override
  public Mono<BinResponse> lookup(String number) {
    return Mono.just(BinResponse.builder().build());
  }
}
