package com.deviget.minesweeper.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {

  private CellFactory cellFactory = new CellFactory();

  @Test
  public void revealUnFlagged() {
    Cell cell = cellFactory.createUnFLaggedUnrevealed();
    Assertions.assertTrue(cell.reveal());
  }

  @Test
  public void revealFlagged() {
    Cell cell = cellFactory.createFLaggedUnrevealed();
    Assertions.assertFalse(cell.reveal());
  }

  @Test
  public void revealMine() {
    Cell cell = cellFactory.createMine();
    Assertions.assertFalse(cell.reveal());
  }

  @Test
  public void testFlagged() {
    Cell cell = cellFactory.createFLaggedUnrevealed();
    Assertions.assertTrue(cell.flagged());
  }

  @Test
  public void testUnFlagged() {
    Cell cell = cellFactory.createUnFLaggedUnrevealed();
    Assertions.assertFalse(cell.flagged());
  }

}
