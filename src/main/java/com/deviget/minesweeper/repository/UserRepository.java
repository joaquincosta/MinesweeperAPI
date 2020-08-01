package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.User;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

  private List<User> users = new ArrayList<>();

  public void store(final User user) {
    users.add(user);
  }

  public Optional<User> retrieve(final String username) {
    return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
  }

}
