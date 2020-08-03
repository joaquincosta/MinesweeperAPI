package com.deviget.minesweeper.controller;

import com.deviget.minesweeper.dto.ErrorDTO;
import com.deviget.minesweeper.exception.BoardNotFoundException;
import com.deviget.minesweeper.exception.DuplicatedUserException;
import com.deviget.minesweeper.exception.FinishedGameException;
import com.deviget.minesweeper.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MinesweeperControllerAdvice {

  @ExceptionHandler(value = {IllegalArgumentException.class,
      DuplicatedUserException.class, FinishedGameException.class})
  protected ResponseEntity<ErrorDTO> handleBadRequest(final RuntimeException ex) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(createResponse(httpStatus, ex), httpStatus);
  }

  @ExceptionHandler(value = {BoardNotFoundException.class, UserNotFoundException.class})
  protected ResponseEntity<ErrorDTO> handleNotFound(final RuntimeException ex) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(createResponse(httpStatus, ex), httpStatus);
  }

  @ExceptionHandler(value = {RuntimeException.class})
  protected ResponseEntity<ErrorDTO> genericHandle(final RuntimeException ex) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(createResponse(httpStatus, ex), httpStatus);
  }

  private ErrorDTO createResponse(final HttpStatus status, final Exception ex) {
    return ErrorDTO.builder().code(status.value()).message(ex.getMessage()).build();
  }
}
