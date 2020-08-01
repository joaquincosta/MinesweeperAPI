package com.deviget.minesweeper.exception;

public class BoardAndUserNotFoundException  extends RuntimeException {

  private static final String ERROR_MSG = "Board: %s for User: %s not found";

  public BoardAndUserNotFoundException(final String board, final String user) {
    super(String.format(ERROR_MSG, board, user));
  }
}
