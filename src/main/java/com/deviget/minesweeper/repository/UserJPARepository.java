package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends CrudRepository<User, Integer> {
  Optional<User> findByName(String username);
}
