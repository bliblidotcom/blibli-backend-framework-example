package com.blibli.oss.backend.example.streaming.repository;

import com.blibli.oss.backend.example.streaming.model.CustomerEvent;
import com.blibli.oss.backend.kafka.repository.AbstractKafkaRepository;
import com.blibli.oss.backend.kafka.repository.KafkaRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerKafkaRepository extends AbstractKafkaRepository<CustomerEvent> implements KafkaRepository<CustomerEvent> {

}
