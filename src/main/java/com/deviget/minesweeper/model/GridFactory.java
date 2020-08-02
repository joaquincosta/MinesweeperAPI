package com.deviget.minesweeper.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GridFactory {

  public List<List<Cell>> create(final Integer rows, final Integer columns, final Integer mines) {
    Integer totalCells = rows * columns;
    List<Integer> minesPositions = createMinesRandomPosition(mines, totalCells);
    List<List<Cell>> grid = new ArrayList<>();
    for (int row = 0; row < rows; row++) {
      List<Cell> rowCells = new ArrayList<>();
      for (int column = 0; column < columns; column++) {
        Integer position = (columns * row) + column;
        Boolean isMine = minesPositions.contains(position);
        rowCells.add(Cell.builder().mark(MarkType.NONE).revealed(Boolean.FALSE).isMine(isMine).build());
      }
      grid.add(rowCells);
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
