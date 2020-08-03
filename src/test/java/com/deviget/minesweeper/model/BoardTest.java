package com.deviget.minesweeper.model;

import com.deviget.minesweeper.commons.BoardStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

  private BoardFactory boardFactory = new BoardFactory();

  @Test
  public void testClickInBomb() {
    Board board = boardFactory.createWithOneMineInZeroZero();
    board.click(0,0);
    Assertions.assertTrue(BoardStatus.LOSE.equals(board.getStatus()));
  }

  @Test
  public void testWinWithMark() {
    Board board = boardFactory.createWithOneMineInZeroZero();
    board.click(2,2);
    Assertions.assertTrue(BoardStatus.PLAYING.equals(board.getStatus()));
    board.mark(0,0, MarkType.FLAG);
    Assertions.assertTrue(BoardStatus.WON.equals(board.getStatus()));
  }

  @Test
  public void testWinWithClik(){
    Board board = boardFactory.createWithOneMineInZeroZero();
    board.mark(0,0, MarkType.FLAG);
    Assertions.assertTrue(BoardStatus.PLAYING.equals(board.getStatus()));
    board.click(2,2);
    Assertions.assertTrue(BoardStatus.WON.equals(board.getStatus()));
  }

  @Test
  public void testPlayingWithQuestion() {
    Board board = boardFactory.createWithOneMineInZeroZero();
    board.mark(0,0, MarkType.QUESTION);
    Assertions.assertTrue(BoardStatus.PLAYING.equals(board.getStatus()));
    board.click(2,2);
    Assertions.assertTrue(BoardStatus.PLAYING.equals(board.getStatus()));
  }

  @Test
  public void testReveal() {
    Board board = boardFactory.createWithOneMineInTwoTwo();
    board.click(0,0);
    // bomb position
    Assertions.assertFalse(board.getGrid().get(2).get(2).getRevealed());
    // unrevealed positions
    Assertions.assertFalse(board.getGrid().get(3).get(2).getRevealed());
    Assertions.assertFalse(board.getGrid().get(3).get(3).getRevealed());
    Assertions.assertFalse(board.getGrid().get(2).get(3).getRevealed());
    // revealed positions

    Assertions.assertTrue(board.getGrid().get(0).get(0).getRevealed());
    Assertions.assertTrue(board.getGrid().get(0).get(1).getRevealed());
    Assertions.assertTrue(board.getGrid().get(0).get(2).getRevealed());
    Assertions.assertTrue(board.getGrid().get(0).get(3).getRevealed());

    Assertions.assertTrue(board.getGrid().get(1).get(0).getRevealed());
    Assertions.assertTrue(board.getGrid().get(1).get(1).getRevealed());
    Assertions.assertTrue(board.getGrid().get(1).get(2).getRevealed());
    Assertions.assertTrue(board.getGrid().get(1).get(3).getRevealed());

    Assertions.assertTrue(board.getGrid().get(2).get(0).getRevealed());
    Assertions.assertTrue(board.getGrid().get(2).get(1).getRevealed());

    Assertions.assertTrue(board.getGrid().get(3).get(0).getRevealed());
    Assertions.assertTrue(board.getGrid().get(3).get(1).getRevealed());
  }
}
