package com.blibli.oss.backend.example.repository;

import com.blibli.oss.backend.example.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

  Mono<Long> countByEmail(String email);

}
