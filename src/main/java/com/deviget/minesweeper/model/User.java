package com.deviget.minesweeper.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User {
  private String username;
  private List<String> boards;
}
