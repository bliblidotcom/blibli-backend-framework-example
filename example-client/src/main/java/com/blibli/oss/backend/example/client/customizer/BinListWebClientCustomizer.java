package com.blibli.oss.backend.example.client.customizer;

import com.blibli.oss.backend.apiclient.customizer.ApiClientWebClientCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BinListWebClientCustomizer implements ApiClientWebClientCustomizer {

  @Override
  public void customize(WebClient.Builder builder) {
    builder.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
  }
}
