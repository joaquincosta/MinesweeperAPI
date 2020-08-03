package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

  private List<User> users = new ArrayList<>();

  public void store(final User user) {
    users.add(user);
  }

  public Optional<User> retrieve(final String username) {
    return users.stream().filter(user -> user.getName().equals(username)).findFirst();
  }

}
