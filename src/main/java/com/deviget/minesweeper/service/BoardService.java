package com.deviget.minesweeper.service;

import com.deviget.minesweeper.exception.BoardNotFoundException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  public String createBoard(final Integer rows, final Integer columns, final Integer mines) {
    String id = UUID.randomUUID().toString();
    Board board = new Board();
    board.setId(id);
    boardRepository.store(board);
    return id;
  }

  public Board retrieveBoard(final String boardId) {
    Optional<Board> optionalBoard = boardRepository.retrieve(boardId);
    if (optionalBoard.isEmpty()){
      throw new BoardNotFoundException(boardId);
    }
    return optionalBoard.get();
  }
}
