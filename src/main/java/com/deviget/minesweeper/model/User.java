package com.deviget.minesweeper.model;

import lombok.*;
import org.hibernate.type.StandardBasicTypes;

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
@Entity(name = "player")
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;
  private String name;
  @ElementCollection
  private List<Integer> boards;
}
