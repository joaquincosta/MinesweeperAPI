package com.deviget.minesweeper.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GridFactory {

  public List<Row> create(final Integer rows, final Integer columns, final Integer mines) {
    Integer totalCells = rows * columns;
    List<Integer> minesPositions = createMinesRandomPosition(mines, totalCells);
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

  private List<Integer> createMinesRandomPosition(final Integer mines, final Integer totalCells) {
    return Stream.iterate(0, i -> i++).limit(mines)
        .map(i -> createRandomPosition(totalCells)).collect(Collectors.toList());
  }

  private Integer createRandomPosition(final Integer totalCells) {
    return (int) ((Math.random() * (totalCells)));
  }
}
