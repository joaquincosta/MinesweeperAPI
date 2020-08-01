package com.deviget.minesweeper.repository;

import com.deviget.minesweeper.model.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

  private List<Board> boards = new ArrayList<>();


  public void store(final Board board) {
    boards.add(board);
  }

  public Optional<Board> retrieve(final String boardId) {
    return boards.stream().filter(board -> board.getId().equals(boardId)).findFirst();
  }
}
