package com.deviget.minesweeper.exception;

public class FinishedGameException extends RuntimeException {

  private static final String ERROR_MSG = "The board with id: %s cannot be played";

  public FinishedGameException(final Integer boardId) {
    super(String.format(ERROR_MSG, boardId));
  }
}
