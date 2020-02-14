package com.blibli.oss.backend.example.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BinResponse {

  private Number number;

  private String schema;

  private String type;

  private String brand;

  private Boolean prepaid;

  private Country country;

  private Bank bank;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Country {

    private String numeric;

    private String alpha2;

    private String name;

    private String emoji;

    private String currency;

    private Integer latitude;

    private Integer longitude;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Bank {

    private String name;

    private String url;

    private String phone;

    private String city;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Number {

    private Integer length;

    private Boolean luhn;

  }
}
