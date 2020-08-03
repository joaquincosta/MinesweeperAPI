package com.deviget.minesweeper.facade;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.CreateBoardBody;
import com.deviget.minesweeper.dto.CreateUserBody;
import com.deviget.minesweeper.dto.IdDTO;
import com.deviget.minesweeper.dto.UpdateBoardBody;
import com.deviget.minesweeper.dto.UserDTO;
import com.deviget.minesweeper.exception.BoardAndUserNotMatch;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.User;
import com.deviget.minesweeper.service.BoardService;
import com.deviget.minesweeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MinesweeperFacade {

  @Autowired
  private BoardService boardService;
  @Autowired
  private UserService userService;
  @Autowired
  private ConversionService conversionService;

  public IdDTO createBoard(final CreateBoardBody body, final String username) {
    Assert.hasLength(username, "Username cannot be Empty");
    Assert.notNull(body, "Create board body cannot be null");
    Assert.isTrue(body.getRows() > 0, "Rows quantity must be greater than 0");
    Assert.isTrue(body.getColumns() > 0, "Columns quantity must be greater than 0");
    Assert.isTrue(body.getMines() > 0 && body.getMines() < body.getRows() * body.getColumns(),
        "Mines quantity must be between 0 and board size (Rows x Columns)");
    Integer id = boardService.createBoard(body.getRows(), body.getColumns(), body.getMines());
    userService.addBoardToUser(username, id);
    return conversionService.convert(id, IdDTO.class);
  }

  public BoardDTO retrieveBoard(final Integer boardId, final String username) {
    Assert.notNull(boardId, "BoardId cannot be null");
    Assert.hasLength(username, "Username cannot be Empty");
    if (!userService.matchUserWithBoard(username, boardId)) {
      throw new BoardAndUserNotMatch(boardId, username);
    }
    return conversionService.convert(boardService.retrieveBoard(boardId), BoardDTO.class);
  }

  public void createUser(final CreateUserBody body) {
    Assert.notNull(body, "CreateUserBody cannot be null");
    Assert.hasLength(body.getUsername(), "Username cannot be Empty");
    userService.createUser(body.getUsername());
  }

  public UserDTO retrieveUser(final String userName) {
    Assert.hasLength(userName, "Username cannot be Empty");
    User user = userService.findUser(userName);
    return conversionService.convert(user, UserDTO.class);
  }

  public BoardDTO updateBoard(final UpdateBoardBody body, final Integer boardId, final String username) {
    Assert.notNull(boardId, "BoardId cannot be null");
    Assert.hasLength(username, "Username cannot be Empty");
    Assert.notNull(body, "UpdateBoardBody cannot be null");
    Assert.isTrue(body.getRow() >= 0, "Row must be greater or equal than 0");
    Assert.isTrue(body.getColumn() >= 0, "Column must be greater or equal than 0");
    Assert.notNull(body.getType(), "Update Type cannot be null");
    if (!userService.matchUserWithBoard(username, boardId)) {
      throw new BoardAndUserNotMatch(boardId, username);
    }
    Board updatedBoard = boardService.updateBoard(boardId, body.getRow(), body.getColumn(), body.getType());
    return conversionService.convert(updatedBoard, BoardDTO.class);
  }
}
