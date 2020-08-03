package com.deviget.minesweeper.controller;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.CreateBoardBody;
import com.deviget.minesweeper.dto.CreateUserBody;
import com.deviget.minesweeper.dto.IdDTO;
import com.deviget.minesweeper.dto.UpdateBoardBody;
import com.deviget.minesweeper.dto.UserDTO;
import com.deviget.minesweeper.facade.MinesweeperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/minesweeper")
public class MinesweeperController {

  @Autowired
  private MinesweeperFacade minesweeperFacade;

  @ResponseBody
  @PostMapping("/boards")
  public ResponseEntity<IdDTO> createBoard(@RequestBody CreateBoardBody body, @RequestParam String username) {
    IdDTO idDTO = minesweeperFacade.createBoard(body, username);
    return new ResponseEntity<>(idDTO, HttpStatus.OK);
  }

  @ResponseBody
  @GetMapping("/boards/{boardId}")
  public ResponseEntity<BoardDTO> getBoard(@PathVariable Integer boardId, @RequestParam String username) {
    BoardDTO boardDTO = minesweeperFacade.retrieveBoard(boardId, username);
    return new ResponseEntity<>(boardDTO, HttpStatus.OK);
  }

  @ResponseBody
  @PatchMapping("/boards/{boardId}")
  public ResponseEntity<BoardDTO> updateBoard(@RequestBody UpdateBoardBody body, @PathVariable Integer boardId,
                                              @RequestParam String username) {
    BoardDTO boardDTO = minesweeperFacade.updateBoard(body, boardId, username);
    return new ResponseEntity<>(boardDTO, HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity createUser(@RequestBody CreateUserBody body) {
    minesweeperFacade.createUser(body);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/users/{userName}")
  public ResponseEntity<UserDTO> getUser(@PathVariable String userName) {
    UserDTO userDTO = minesweeperFacade.retrieveUser(userName);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }
}
