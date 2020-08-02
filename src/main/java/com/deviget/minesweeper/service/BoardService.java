package com.deviget.minesweeper.service;

import com.deviget.minesweeper.commons.BoardStatus;
import com.deviget.minesweeper.commons.UpdateBoardType;
import com.deviget.minesweeper.exception.BoardNotFoundException;
import com.deviget.minesweeper.exception.FinishedGameException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.Cell;
import com.deviget.minesweeper.model.GridFactory;
import com.deviget.minesweeper.model.MarkType;
import com.deviget.minesweeper.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BoardService {

  @Autowired
  private GridFactory gridFactory;
  @Autowired
  private BoardRepository boardRepository;

  public String createBoard(final Integer rows, final Integer columns, final Integer mines) {
    String id = UUID.randomUUID().toString();
    Board board = new Board();
    board.setStatus(BoardStatus.PLAYING);
    board.setId(id);
    List<List<Cell>> grid = gridFactory.create(rows, columns, mines);
    board.setGrid(grid);
    boardRepository.store(board);
    return id;
  }

  public Board retrieveBoard(final String boardId) {
    Optional<Board> optionalBoard = boardRepository.retrieve(boardId);
    if (optionalBoard.isEmpty()) {
      throw new BoardNotFoundException(boardId);
    }
    return optionalBoard.get();
  }

  public Board updateBoard(final String boardId, final Integer row, final Integer column, final String type) {
    Board board = retrieveBoard(boardId);
    if (!BoardStatus.PLAYING.equals(board.getStatus())) {
      throw new FinishedGameException(boardId);
    }
    UpdateBoardType updateType = UpdateBoardType.valueOf(type);
    if (!UpdateBoardType.REVEAL.equals(updateType)) {
      return board.mark(row, column, MarkType.valueOf(type));
    }
    board = board.click(row, column);

    return board;
  }
}
