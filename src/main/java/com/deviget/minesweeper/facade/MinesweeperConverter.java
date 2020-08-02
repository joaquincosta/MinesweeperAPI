package com.deviget.minesweeper.facade;

import com.deviget.minesweeper.commons.CellStatus;
import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.CellDTO;
import com.deviget.minesweeper.dto.IdDTO;
import com.deviget.minesweeper.dto.UserBoardDTO;
import com.deviget.minesweeper.dto.UserDTO;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.Cell;
import com.deviget.minesweeper.model.MarkType;
import com.deviget.minesweeper.model.Row;
import com.deviget.minesweeper.model.User;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MinesweeperConverter {

  public static class IdDtoConverter implements Converter<String, IdDTO> {
    @Override
    public IdDTO convert(final String id) {
      return new IdDTO(id);
    }
  }

  public static class BoardDTOConverter implements Converter<Board, BoardDTO> {

    @Override
    public BoardDTO convert(final Board board) {
      BoardDTO boardDTO = new BoardDTO();
      boardDTO.setStatus(board.getStatus().name());
      List<CellDTO> cells = new ArrayList<>();
      List<Row> grid = board.getGrid();
      for (int row = 0 ; row < grid.size() ; row++) {
        for (int column = 0 ; column < grid.get(row).getColumns().size() ; column++) {
          cells.add(convertCell(grid.get(row).get(column), row, column));
        }
      }
      boardDTO.setCells(cells);
      return boardDTO;
    }

    private CellDTO convertCell(final Cell cell, final Integer row, final Integer column) {
      CellDTO cellDTO = new CellDTO();
      cellDTO.setRow(row);
      cellDTO.setColumn(column);
      String status;
      if (MarkType.NONE.equals(cell.getMark())) {
        status = cell.getRevealed() ? CellStatus.REVEALED.name() : CellStatus.HIDE.name();
      } else {
        status = MarkType.FLAG.equals(cell.getMark()) ? MarkType.FLAG.name() : MarkType.QUESTION.name();
      }
      cellDTO.setStatus(status);
      return cellDTO;
    }
  }

  public static class UserDTOConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(final User user) {
      UserDTO userDTO = new UserDTO();
      userDTO.setBoards(user.getBoards().stream()
          .map(board -> UserBoardDTO.builder().id(board).build())
          .collect(Collectors.toList()));
      return userDTO;
    }
  }

  public static Set<Converter> all() {
    return Set.of(
        new IdDtoConverter(),
        new BoardDTOConverter(),
        new UserDTOConverter()
        );
  }

}
