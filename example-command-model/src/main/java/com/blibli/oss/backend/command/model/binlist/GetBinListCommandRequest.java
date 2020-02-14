package com.blibli.oss.backend.command.model.binlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBinListCommandRequest {

  @NotBlank(message = "NotBlank")
  @Digits(message = "MustDigit", integer = 8, fraction = 0)
  private String number;
}
