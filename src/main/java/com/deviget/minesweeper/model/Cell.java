package com.deviget.minesweeper.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Cell {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
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
