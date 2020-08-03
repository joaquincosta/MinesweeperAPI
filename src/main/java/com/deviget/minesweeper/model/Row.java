package com.deviget.minesweeper.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
  private Integer id;
  @ElementCollection
  @Cascade(value = CascadeType.ALL)
  private List<Cell> columns;

  public Cell get(Integer i){
    return columns.get(i);
  }
}
