package com.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TimerTask;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Task extends TimerTask {
  @Getter
  @Setter
  boolean isActive = false;
  @Getter
  @Setter
  String url = null;
}
