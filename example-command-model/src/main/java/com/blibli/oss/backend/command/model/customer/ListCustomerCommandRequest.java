package com.blibli.oss.backend.command.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerCommandRequest {

  @NotNull(message = "NotNull")
  private Integer size;

  @NotNull(message = "NotNull")
  private Integer page;

  @Builder.Default
  private Map<String, String> orders = new HashMap<>();
}
