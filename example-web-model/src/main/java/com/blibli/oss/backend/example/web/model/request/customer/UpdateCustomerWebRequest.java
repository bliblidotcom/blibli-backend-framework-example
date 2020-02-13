package com.blibli.oss.backend.example.web.model.request.customer;

import com.blibli.oss.backend.example.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerWebRequest {

  private Gender gender;

  private String firstName;

  private String lastName;

  private String email;
}
