package com.deviget.minesweeper.service;

import com.deviget.minesweeper.exception.DuplicatedUserException;
import com.deviget.minesweeper.exception.UserNotFoundException;
import com.deviget.minesweeper.model.User;
import com.deviget.minesweeper.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserJPARepository userRepository;

  public void createUser(final String username) {
    Optional<User> optionalUser = userRepository.findByName(username);
    if (!optionalUser.isEmpty()) {
      throw new DuplicatedUserException(username);
    }
    User user = new User();
    user.setName(username);
    user.setBoards(new ArrayList<>());
    userRepository.save(user);
  }

  public User findUser(final String username) {
    Optional<User> optionalUser = userRepository.findByName(username);
    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException(username);
    }
    return optionalUser.get();
  }

  public void addBoardToUser(final String username, final Integer boardId) {
    User user = findUser(username);
    user.getBoards().add(boardId);
    userRepository.save(user);
  }

  public Boolean matchUserWithBoard(final String username, final Integer boardId) {
    User user = findUser(username);
    return user.getBoards().stream().anyMatch(userBoard -> userBoard.equals(boardId));
  }
}
