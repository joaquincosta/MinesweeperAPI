package com.deviget.minesweeper.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Cell {

  private Boolean revealed;
  private Boolean isMine;
  private MarkType mark;

  private boolean marked() {
    return !MarkType.NONE.equals(mark);
  }

  public boolean flagged() {
    return MarkType.FLAG.equals(mark);
  }

  public Boolean reveal() {
    if (revealed || isMine || marked()) {
      return Boolean.FALSE;
    }
    revealed = Boolean.TRUE;
    return Boolean.TRUE;
  }

}
