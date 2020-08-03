package com.deviget.minesweeper.exception;

public class BoardNotFoundException extends RuntimeException {

  private static final String ERROR_MSG = "Board %s not found";

  public BoardNotFoundException(final Integer board) {
    super(String.format(ERROR_MSG, board));
  }
}
