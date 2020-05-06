package com.blibli.oss.backend.example.streaming.model;

import com.blibli.oss.backend.example.constant.KafkaTopics;
import com.blibli.oss.backend.example.enums.Gender;
import com.blibli.oss.backend.kafka.annotation.KafkaKey;
import com.blibli.oss.backend.kafka.annotation.KafkaTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@KafkaTopic(KafkaTopics.CUSTOMER_EVENT)
public class CustomerEvent {

  @KafkaKey
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
