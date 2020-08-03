package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJPARepository extends CrudRepository<Board, Integer> {
}
