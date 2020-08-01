package com.deviget.minesweeper.model;

import com.deviget.minesweeper.exception.PumException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Cell {

  private Boolean revealed;
  private Boolean isMine;
  private MarkType mark;
  private List<Cell> neighbors;

  public void click(){
    if (revealed || marked()) {
      return;
    }
    if (isMine) {
      throw new PumException();
    }
    revealed = Boolean.TRUE;
    if (neighbors.stream().noneMatch(Cell::getIsMine)) {
      neighbors.stream().forEach(Cell::reveal);
    }
  }

  private boolean marked() {
    return !MarkType.NONE.equals(mark);
  }

  private void reveal() {
    if (isMine || revealed || marked()) {
      return;
    }
    if (neighbors.stream().noneMatch(Cell::getIsMine)) {
      revealed = Boolean.TRUE;
      neighbors.stream().forEach(Cell::reveal);
    }
  }

}
