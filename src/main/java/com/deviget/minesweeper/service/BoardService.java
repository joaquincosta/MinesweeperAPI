package com.deviget.minesweeper.service;

import com.deviget.minesweeper.commons.BoardStatus;
import com.deviget.minesweeper.commons.UpdateBoardType;
import com.deviget.minesweeper.exception.BoardNotFoundException;
import com.deviget.minesweeper.exception.PumException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.Cell;
import com.deviget.minesweeper.model.MarkType;
import com.deviget.minesweeper.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  public String createBoard(final Integer rows, final Integer columns, final Integer mines) {
    String id = UUID.randomUUID().toString();
    Board board = new Board();
    board.setStatus(BoardStatus.PLAYING);
    board.setId(id);
    List<List<Cell>> grid = createGrid(rows, columns, mines);
    board.setGrid(grid);
    boardRepository.store(board);
    return id;
  }

  private List<List<Cell>> createGrid(final Integer rows, final Integer columns, final Integer mines) {
    Integer totalCells = rows * columns;
    List<Integer> minesPositions = createMinesRandomPosition(mines, totalCells);
    List<List<Cell>> grid = new ArrayList<>();
    for (int row = 0; row < rows; row++) {
      List<Cell> rowCells = new ArrayList<>();
      for (int column = 0; column < columns; column++) {
        Integer position = (columns * row) + column;
        Boolean isMine = minesPositions.contains(position);
        rowCells.add(Cell.builder().mark(MarkType.NONE).revealed(Boolean.FALSE).isMine(isMine).build());
      }
      grid.add(rowCells);
    }
    return grid;
  }

  private List<Integer> createMinesRandomPosition(final Integer mines, final Integer totalCells) {
    return Stream.iterate(0, i->i++).limit(mines)
        .map(i-> createRamdom(totalCells)).collect(Collectors.toList());
  }

  private Integer createRamdom(final Integer totalCells) {
    return (int) ((Math.random() * (totalCells)));
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
    UpdateBoardType updateType = UpdateBoardType.valueOf(type);
    if (!UpdateBoardType.REVEAL.equals(updateType)) {
      return board.mark(row, column, MarkType.valueOf(type));
    }
    try {
      board = board.click(row, column);
    } catch (PumException e) {
      board.setStatus(BoardStatus.GAME_OVER);
    }
    return board;
  }
}
