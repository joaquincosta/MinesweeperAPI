package com.deviget.minesweeper.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class GridFactory {

  public List<Row> create(final Integer rows, final Integer columns, final Integer mines) {
    Integer totalCells = rows * columns;
    Set<Integer> minesPositions = createMinesRandomPosition(mines, totalCells);
    List<Row> grid = new ArrayList<>();
    for (int row = 0; row < rows; row++) {
      List<Cell> rowColumns = new ArrayList<>();
      for (int column = 0; column < columns; column++) {
        Integer position = (columns * row) + column;
        Boolean isMine = minesPositions.contains(position);
        Cell cell = new Cell();
        cell.setMark(MarkType.NONE);
        cell.setRevealed(Boolean.FALSE);
        cell.setIsMine(isMine);
        rowColumns.add(cell);
      }
      Row gridRow = new Row();
      gridRow.setColumns(rowColumns);
      grid.add(gridRow);
    }
    return grid;
  }

  private Set<Integer> createMinesRandomPosition(final Integer mines, final Integer totalCells) {
    Set<Integer> minesPositions = new HashSet<>();
    while (minesPositions.size() < mines) {
      minesPositions.add(createRandomPosition(totalCells));
    }
    return minesPositions;
  }

  private Integer createRandomPosition(final Integer totalCells) {
    return new Random().ints(0,totalCells).findFirst().getAsInt();
  }
}
