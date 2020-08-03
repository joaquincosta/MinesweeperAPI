package com.deviget.minesweeper.exception;

public class BoardAndUserNotMatch extends RuntimeException {

  private static final String ERROR_MSG = "Board: %s for User: %s not found";

  public BoardAndUserNotMatch(final Integer board, final String user) {
    super(String.format(ERROR_MSG, board, user));
  }
}
