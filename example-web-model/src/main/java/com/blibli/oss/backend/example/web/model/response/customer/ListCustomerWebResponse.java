package com.blibli.oss.backend.example.web.model.response.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerWebResponse {

  private Long total;

  private List<GetCustomerWebResponse> customers;
}
