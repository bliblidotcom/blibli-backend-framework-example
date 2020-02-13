package com.blibli.oss.backend.command.model.customer;

import com.blibli.oss.backend.example.enums.Gender;
import com.blibli.oss.backend.example.validation.CustomerMustExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerCommandRequest {

  @CustomerMustExists(message = "NotFound")
  @NotBlank(message = "NotBlank")
  private String id;

  @NotNull(message = "NotNull")
  private Gender gender;

  @NotBlank(message = "NotBlank")
  @Size(max = 100, message = "TooLarge")
  private String firstName;

  @Size(max = 100, message = "TooLarge")
  private String lastName;

  @Email(message = "MustEmail")
  @NotBlank(message = "NotBlank")
  @Size(max = 100, message = "TooLarge")
  private String email;
}
