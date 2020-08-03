package com.deviget.minesweeper.model;

import com.deviget.minesweeper.commons.BoardStatus;

import java.util.List;

public class BoardFactory {

  private GridFactory gridFactory = new GridFactory();

  public Board createWithOneMineInZeroZero() {
    List<Row> rows = gridFactory.create(3, 3, 0);
    rows.get(0).get(0).setIsMine(true);
    Board board = new Board();
    board.setId(1);
    board.setStatus(BoardStatus.PLAYING);
    board.setGrid(rows);
    return board;
  }

  public Board createWithOneMineInTwoTwo() {
    List<Row> rows = gridFactory.create(4, 4, 0);
    rows.get(2).get(2).setIsMine(true);
    Board board = new Board();
    board.setId(1);
    board.setStatus(BoardStatus.PLAYING);
    board.setGrid(rows);
    return board;
  }

}
