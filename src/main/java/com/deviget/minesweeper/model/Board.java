package com.deviget.minesweeper.model;

import com.deviget.minesweeper.commons.BoardStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Board {

  private String id;
  private BoardStatus status;
  private List<List<Cell>> grid;

  public Board click(final Integer row, final Integer column) {
    grid.get(row).get(column).click();
    return this;
  }

  public Board mark(final Integer row, final Integer column, final MarkType markType) {
    grid.get(row).get(column).setMark(markType);
    return this;
  }
}
