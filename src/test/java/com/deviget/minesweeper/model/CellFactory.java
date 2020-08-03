package com.deviget.minesweeper.model;

public class CellFactory {

  public Cell createFLaggedUnrevealed() {
    Cell cell = new Cell();
    cell.setIsMine(false);
    cell.setRevealed(false);
    cell.setMark(MarkType.FLAG);
    return cell;
  }

  public Cell createUnFLaggedUnrevealed() {
    Cell cell = new Cell();
    cell.setIsMine(false);
    cell.setRevealed(false);
    cell.setMark(MarkType.NONE);
    return cell;
  }

  public Cell createMine() {
    Cell cell = new Cell();
    cell.setIsMine(true);
    cell.setRevealed(false);
    cell.setMark(MarkType.NONE);
    return cell;
  }
}
