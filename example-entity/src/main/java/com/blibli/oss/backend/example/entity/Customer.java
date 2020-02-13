package com.blibli.oss.backend.example.entity;

import com.blibli.oss.backend.example.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("customers")
public class Customer {

  @Id
  private String id;

  private Gender gender;

  private String firstName;

  private String lastName;

  private String email;

  @LastModifiedDate
  private Long lastModifiedDate;

  @LastModifiedBy
  private String lastModifiedBy;

  @CreatedDate
  private Long createdDate;

  @CreatedBy
  private String createdBy;
}
