package com.deviget.minesweeper.exception;

public class DuplicatedUserException extends RuntimeException {

  private static final String ERROR_MSG = "Username %s already exist";

  public DuplicatedUserException(final String username) {
    super(String.format(ERROR_MSG, username));
  }
}
