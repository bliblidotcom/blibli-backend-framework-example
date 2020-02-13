package com.blibli.oss.backend.command.model.customer;

import com.blibli.oss.backend.example.validation.CustomerMustExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerCommandRequest {

  @CustomerMustExists(message = "NotFound")
  @NotBlank(message = "NotBlank")
  private String id;

}
