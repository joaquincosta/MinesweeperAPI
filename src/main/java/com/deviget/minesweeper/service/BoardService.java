package com.deviget.minesweeper.service;

import com.deviget.minesweeper.commons.BoardStatus;
import com.deviget.minesweeper.commons.UpdateBoardType;
import com.deviget.minesweeper.exception.BoardNotFoundException;
import com.deviget.minesweeper.exception.FinishedGameException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.GridFactory;
import com.deviget.minesweeper.model.MarkType;
import com.deviget.minesweeper.model.Row;
import com.deviget.minesweeper.repository.BoardJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

  @Autowired
  private GridFactory gridFactory;
  @Autowired
  private BoardJPARepository boardRepository;

  public Integer createBoard(final Integer rows, final Integer columns, final Integer mines) {
    Board board = new Board();
    board.setStatus(BoardStatus.PLAYING);
    List<Row> grid = gridFactory.create(rows, columns, mines);
    board.setGrid(grid);
    boardRepository.save(board);
    return board.getId();
  }

  public Board retrieveBoard(final Integer boardId) {
    Optional<Board> optionalBoard = boardRepository.findById(boardId);
    if (optionalBoard.isEmpty()) {
      throw new BoardNotFoundException(boardId);
    }
    Board board = optionalBoard.get();
    return board;
  }

  public Board updateBoard(final Integer boardId, final Integer row, final Integer column, final String type) {
    Board board = retrieveBoard(boardId);
    if (!BoardStatus.PLAYING.equals(board.getStatus())) {
      throw new FinishedGameException(boardId);
    }
    UpdateBoardType updateType = UpdateBoardType.valueOf(type);
    if (!UpdateBoardType.REVEAL.equals(updateType)) {
      return board.mark(row, column, MarkType.valueOf(type));
    }
    board = board.click(row, column);
    boardRepository.save(board);
    return board;
  }
}
