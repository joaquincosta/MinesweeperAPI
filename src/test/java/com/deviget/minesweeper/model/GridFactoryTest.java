package com.deviget.minesweeper.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class GridFactoryTest {

  private GridFactory gridFactory = new GridFactory();

  @Test
  public void testCreatedGridSizes() {
    List<Row> rows = gridFactory.create(1, 2, 1);
    Assertions.assertEquals(1, rows.size());
    Assertions.assertEquals(2, rows.get(0).getColumns().size());
    List<Row> rows2 = gridFactory.create(5, 4, 5);
    Assertions.assertEquals(5, rows2.size());
    Assertions.assertEquals(4, rows2.get(0).getColumns().size());
  }

  @Test
  public void testMinesQuantity(){
    List<Row> rows = gridFactory.create(10, 8, 20);
    Assertions.assertEquals(20, countMines(rows));
  }

  private Integer countMines(final List<Row> grid) {
    AtomicReference<Integer> totalMines = new AtomicReference<>(0);
    grid.stream().forEach(column -> {
      List<Cell> mines = column.getColumns().stream().filter(Cell::getIsMine).collect(Collectors.toList());
      totalMines.set(totalMines.get() + mines.size());
    });
    return totalMines.get();
  }

}
