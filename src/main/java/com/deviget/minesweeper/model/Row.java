package com.deviget.minesweeper.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Row {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  @ElementCollection
  private List<Cell> columns;

  public Cell get(Integer i){
    return columns.get(i);
  }
}
