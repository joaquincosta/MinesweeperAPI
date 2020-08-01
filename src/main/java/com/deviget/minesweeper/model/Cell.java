package com.deviget.minesweeper.model;

import com.deviget.minesweeper.exception.PumException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Cell {

  private Boolean revealed;
  private Boolean isBomb;
  private MarkType mark;
  private List<Cell> neighbors;

  public void click(){
    if (revealed || marked()) {
      return;
    }
    if (isBomb) {
      throw new PumException();
    }
    revealed = Boolean.TRUE;
    if (neighbors.stream().noneMatch(Cell::getIsBomb)) {
      neighbors.stream().forEach(Cell::reveal);
    }
  }

  private boolean marked() {
    return !MarkType.NONE.equals(mark);
  }

  private void reveal() {
    if (isBomb || revealed || marked()) {
      return;
    }
    if (neighbors.stream().noneMatch(Cell::getIsBomb)) {
      revealed = Boolean.TRUE;
      neighbors.stream().forEach(Cell::reveal);
    }
  }

}
