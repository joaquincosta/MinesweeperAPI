package com.deviget.minesweeper.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Cell {

  private Boolean revealed;
  private Boolean isMine;
  private MarkType mark;

  private boolean marked() {
    return !MarkType.NONE.equals(mark);
  }

  public Boolean reveal() {
    if (revealed || isMine || marked()) {
      return Boolean.FALSE;
    }
    revealed = Boolean.TRUE;
    return Boolean.TRUE;
  }

}
