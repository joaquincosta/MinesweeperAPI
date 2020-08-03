package com.deviget.minesweeper.model;

import com.deviget.minesweeper.commons.BoardStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Entity
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private BoardStatus status;
  @OneToMany
  @Cascade(value = CascadeType.ALL)
  private List<Row> grid;

  public Board click(final Integer row, final Integer column) {
    Cell cell = grid.get(row).get(column);
    if (cell.getIsMine()) {
      status = BoardStatus.GAME_OVER;
      return this;
    }
    reveal(row, column);
    if (boardCompleted()) {
      status = BoardStatus.WON;
    }
    return this;
  }

  private Boolean boardCompleted() {
    AtomicReference<Integer> mines = new AtomicReference<>(0);
    AtomicReference<Integer> revealedCells = new AtomicReference<>(0);
    grid.stream().forEach(row -> {
      row.getColumns().stream().forEach(cell -> {
        if (cell.getIsMine() && cell.flagged()) {
          mines.getAndSet(mines.get() + 1);
        } else if (cell.getRevealed()) {
          revealedCells.getAndSet(revealedCells.get() + 1);
        }
      });
    });

    return (mines.get() + revealedCells.get()) == (grid.size() * grid.get(0).getColumns().size());
  }

  private void reveal(final Integer row, final Integer column) {
    List<Pair<Integer, Integer>> neighborsPosition = resolveNeighborsPosition(row, column);
    List<Cell> neighborCells = neighborsPosition.stream()
        .map(position -> grid.get(position.getLeft()).get(position.getRight()))
        .collect(Collectors.toList());
    Boolean revealed = grid.get(row).get(column).reveal();
    if (neighborCells.stream().anyMatch(cell -> cell.getIsMine()) || !revealed) {
      return;
    }
    neighborsPosition.stream().forEach(position ->
        reveal(position.getLeft(), position.getRight()));
    //TODO: review reveal cell implementation with test
  }


  private List<Pair<Integer, Integer>> resolveNeighborsPosition(final Integer row, final Integer column) {
    List<Pair<Integer, Integer>> neighborsPosition = new ArrayList<>();
    Pair<Integer, Integer> northWest = Pair.of(row - 1, column - 1);
    if (validPosition(northWest)) {
      neighborsPosition.add(northWest);
    }
    Pair<Integer, Integer> north = Pair.of(row - 1, column);
    if (validPosition(north)) {
      neighborsPosition.add(north);
    }
    Pair<Integer, Integer> northEast = Pair.of(row - 1, column + 1);
    if (validPosition(northEast)) {
      neighborsPosition.add(northEast);
    }
    Pair<Integer, Integer> east = Pair.of(row, column + 1);
    if (validPosition(east)) {
      neighborsPosition.add(east);
    }
    Pair<Integer, Integer> southEast = Pair.of(row + 1, column + 1);
    if (validPosition(southEast)) {
      neighborsPosition.add(southEast);
    }
    Pair<Integer, Integer> south = Pair.of(row + 1, column);
    if (validPosition(south)) {
      neighborsPosition.add(south);
    }
    Pair<Integer, Integer> southWest = Pair.of(row + 1, column - 1);
    if (validPosition(southWest)) {
      neighborsPosition.add(southWest);
    }
    Pair<Integer, Integer> west = Pair.of(row, column - 1);
    if (validPosition(west)) {
      neighborsPosition.add(west);
    }
    return neighborsPosition;
  }

  private Boolean validPosition(final Pair<Integer, Integer> position) {
    Boolean validRow = position.getLeft() >= 0 && position.getLeft() < grid.size();
    Boolean validColumn = position.getRight() >= 0 && position.getRight() < grid.get(0).getColumns().size();
    return validRow && validColumn;
  }

  public Board mark(final Integer row, final Integer column, final MarkType markType) {
    grid.get(row).get(column).setMark(markType);
    if (boardCompleted()) {
      status = BoardStatus.WON;
    }
    return this;
  }
}
