package com.deviget.minesweeper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CellDTO {

  private Integer row;
  private Integer column;
  private String status;

}
