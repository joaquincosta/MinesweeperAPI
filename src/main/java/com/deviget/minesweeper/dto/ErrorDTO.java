package com.deviget.minesweeper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorDTO {
  private Integer code;
  private String message;
}
