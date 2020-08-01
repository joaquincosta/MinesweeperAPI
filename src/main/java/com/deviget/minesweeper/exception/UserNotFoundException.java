package com.deviget.minesweeper.exception;

public class UserNotFoundException extends RuntimeException {

  private static final String ERROR_MSG = "User %s not found";

  public UserNotFoundException(final String username) {
    super(String.format(ERROR_MSG, username));
  }
}
