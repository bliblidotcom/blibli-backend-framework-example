package com.blibli.oss.backend.command.model.example;

public class GetExampleCommandRequest {

  private static final GetExampleCommandRequest INSTANCE = new GetExampleCommandRequest();

  public static GetExampleCommandRequest getInstance() {
    return INSTANCE;
  }
}
