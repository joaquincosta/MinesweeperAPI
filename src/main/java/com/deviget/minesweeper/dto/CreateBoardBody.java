package com.deviget.minesweeper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateBoardBody {
  private Integer rows;
  private Integer columns;
  private Integer mines;
}
