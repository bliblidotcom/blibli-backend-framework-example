package com.blibli.oss.backend.example.web.model.response.customer;

import com.blibli.oss.backend.example.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerWebResponse {

  private String id;

  private Gender gender;

  private String firstName;

  private String lastName;

  private String email;

  private Long lastModifiedDate;

  private String lastModifiedBy;

  private Long createdDate;

  private String createdBy;
}
