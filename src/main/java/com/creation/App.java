package com.creation;


import org.apache.log4j.Logger;


public class App {
  private static final Logger log = Logger.getLogger(App.class);

  public static void main(String[] args) {
    Task task = new AvitoProductsTask();
    MainBot mainBot = new MainBot(task);
    mainBot.setToken("1724743407:AAGMX1kuvCZEz40hnC6m5IqTfPgdGLY-3_g");
    mainBot.connect();
  }
}
