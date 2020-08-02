package com.deviget.minesweeper.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class User {
  @Id
  private String id;
  @ElementCollection
  private List<String> boards;
}
