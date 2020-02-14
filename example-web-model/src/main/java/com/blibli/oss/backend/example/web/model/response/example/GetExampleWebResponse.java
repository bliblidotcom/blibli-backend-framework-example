package com.blibli.oss.backend.example.web.model.response.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetExampleWebResponse {

  private String response;
}
