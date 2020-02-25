package com.blibli.oss.backend.example.client.customizer;

import com.blibli.oss.backend.apiclient.customizer.ApiClientCodecCustomizer;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ExampleCodecCustomizer implements ApiClientCodecCustomizer {

  @Override
  public void customize(ClientCodecConfigurer configurer) {
    configurer.defaultCodecs().enableLoggingRequestDetails(true);
    configurer.defaultCodecs().maxInMemorySize(1000 * 1024);
  }
}
