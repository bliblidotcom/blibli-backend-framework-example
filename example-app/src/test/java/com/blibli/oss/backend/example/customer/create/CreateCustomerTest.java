package com.blibli.oss.backend.example.customer.create;

import com.blibli.oss.backend.example.ExampleApplication;
import com.blibli.oss.backend.example.constant.KafkaTopics;
import com.blibli.oss.backend.example.entity.Customer;
import com.blibli.oss.backend.example.enums.Gender;
import com.blibli.oss.backend.example.repository.CustomerRepository;
import com.blibli.oss.backend.example.web.model.request.customer.CreateCustomerWebRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
  classes = ExampleApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@EmbeddedKafka(
  topics = {KafkaTopics.CUSTOMER_EVENT}
)
public class CreateCustomerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void testCreateCustomerOk() {
    webTestClient.post()
      .uri("/api/customers")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .body(BodyInserters.fromValue(
        CreateCustomerWebRequest.builder()
          .email("test@example.com")
          .firstName("Test")
          .lastName("Test")
          .gender(Gender.MALE)
          .build()))
      .exchange()
      .expectStatus().is2xxSuccessful()
      .expectBody()
      .jsonPath("$.code").isEqualTo(HttpStatus.OK.value())
      .jsonPath("$.status").isEqualTo(HttpStatus.OK.name())
      .jsonPath("$.data.id").exists()
      .jsonPath("$.data.email").isEqualTo("test@example.com")
      .jsonPath("$.data.firstName").isEqualTo("Test")
      .jsonPath("$.data.lastName").isEqualTo("Test")
      .jsonPath("$.data.gender").isEqualTo(Gender.MALE.toString())
      .jsonPath("$.data.lastModifiedDate").exists()
      .jsonPath("$.data.createdDate").exists();
  }

  @Test
  void testCreateCustomerValidationError() {
    webTestClient.post()
      .uri("/api/customers")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .body(BodyInserters.fromValue(
        CreateCustomerWebRequest.builder()
          .email("")
          .firstName("")
          .lastName("")
          .build()))
      .exchange()
      .expectStatus().is4xxClientError()
      .expectBody()
      .jsonPath("$.code").isEqualTo(HttpStatus.BAD_REQUEST.value())
      .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST.name())
      .jsonPath("$.errors").exists();
  }

  @Test
  void testCreateCustomerValidationErrorDuplicateEmail() {
    customerRepository.save(
      Customer.builder()
        .id(UUID.randomUUID().toString())
        .email("unique@example.com")
        .build()
    ).block();

    webTestClient.post()
      .uri("/api/customers")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .body(BodyInserters.fromValue(
        CreateCustomerWebRequest.builder()
          .email("unique@example.com")
          .firstName("Test")
          .lastName("Test")
          .gender(Gender.MALE)
          .build()))
      .exchange()
      .expectStatus().is4xxClientError()
      .expectBody()
      .jsonPath("$.code").isEqualTo(HttpStatus.BAD_REQUEST.value())
      .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST.name())
      .jsonPath("$.errors").exists();
  }
}
