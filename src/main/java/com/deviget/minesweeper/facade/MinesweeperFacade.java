package com.deviget.minesweeper.facade;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.CreateBoardBody;
import com.deviget.minesweeper.dto.CreateUserBody;
import com.deviget.minesweeper.dto.IdDTO;
import com.deviget.minesweeper.dto.UpdateBoardBody;
import com.deviget.minesweeper.dto.UserDTO;
import com.deviget.minesweeper.exception.BoardAndUserNotFoundException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.User;
import com.deviget.minesweeper.service.BoardService;
import com.deviget.minesweeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
public class MinesweeperFacade {

  @Autowired
  private BoardService boardService;
  @Autowired
  private UserService userService;
  @Autowired
  private ConversionService conversionService;

  public IdDTO createBoard(final CreateBoardBody body, final String username) {
    Integer id = boardService.createBoard(body.getRows(), body.getColumns(), body.getMines());
    userService.addBoardToUser(username, id);
    return conversionService.convert(id, IdDTO.class);
  }

  public BoardDTO retrieveBoard(final Integer boardId, final String username) {
    if (!userService.matchUserWithBoard(username, boardId)) {
      throw new BoardAndUserNotFoundException(boardId, username);
    }
    return conversionService.convert(boardService.retrieveBoard(boardId), BoardDTO.class);
  }

  public void createUser(final CreateUserBody body) {
    userService.createUser(body.getUsername());
  }

  public UserDTO retrieveUser(final String userName) {
    User user = userService.findUser(userName);
    return conversionService.convert(user, UserDTO.class);
  }

  public BoardDTO updateBoard(final UpdateBoardBody body, final Integer boardId, final String username) {
    if (!userService.matchUserWithBoard(username, boardId)) {
      throw new BoardAndUserNotFoundException(boardId, username);
    }
    Board updatedBoard = boardService.updateBoard(boardId, body.getRow(), body.getColumn(), body.getType());
    return conversionService.convert(updatedBoard, BoardDTO.class);
  }
}
