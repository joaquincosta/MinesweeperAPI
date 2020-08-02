package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends CrudRepository<User, String> {
}
