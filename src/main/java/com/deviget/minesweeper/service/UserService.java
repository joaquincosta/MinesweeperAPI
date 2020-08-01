package com.deviget.minesweeper.service;

import com.deviget.minesweeper.exception.DuplicatedUserException;
import com.deviget.minesweeper.exception.UserNotFoundException;
import com.deviget.minesweeper.model.Board;
import com.deviget.minesweeper.model.User;
import com.deviget.minesweeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void createUser(final String username) {
    Optional<User> optionalUser = userRepository.retrieve(username);
    if (!optionalUser.isEmpty()) {
      throw new DuplicatedUserException(username);
    }
    userRepository.store(User.builder().username(username).boards(new ArrayList<>()).build());
  }

  public User findUser(final String username) {
    Optional<User> optionalUser = userRepository.retrieve(username);
    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException(username);
    }
    return optionalUser.get();
  }

  public void addBoardToUser(final String username, final String boardId) {
    findUser(username).getBoards().add(boardId);
  }

  public Boolean matchUserWithBoard(final String username, final String boardId) {
    User user = findUser(username);
    return user.getBoards().stream().anyMatch(userBoard -> userBoard.equals(boardId));
  }
}
