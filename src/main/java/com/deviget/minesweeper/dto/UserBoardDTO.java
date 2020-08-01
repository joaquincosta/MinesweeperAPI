package com.deviget.minesweeper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserBoardDTO {

  private String id;
  private String status;

}
