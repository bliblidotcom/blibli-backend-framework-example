package com.blibli.oss.backend.example.command.impl.example;

import com.blibli.oss.backend.command.model.example.GetExampleCommandRequest;
import com.blibli.oss.backend.example.client.ExampleApiClient;
import com.blibli.oss.backend.example.command.example.GetExampleCommand;
import com.blibli.oss.backend.example.web.model.response.example.GetExampleWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetExampleCommandImpl implements GetExampleCommand {

  @Autowired
  private ExampleApiClient exampleApiClient;

  @Override
  public Mono<GetExampleWebResponse> execute(GetExampleCommandRequest request) {
    return exampleApiClient.home()
      .map(this::toWebResponse);
  }

  private GetExampleWebResponse toWebResponse(String response) {
    return GetExampleWebResponse.builder().response(response).build();
  }
}
