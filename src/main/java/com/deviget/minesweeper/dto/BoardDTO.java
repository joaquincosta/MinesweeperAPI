package com.deviget.minesweeper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BoardDTO {

  private String status;
  private List<CellDTO> cells;
}
