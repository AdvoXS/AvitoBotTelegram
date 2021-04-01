package com.creation;

import com.creation.filter.url.RegionFilter;
import com.creation.filter.url.SortProductsFilter;
import com.creation.filter.url.UrlFilter;
import com.creation.filter.url.price.PriceMaxFilter;
import org.apache.log4j.Logger;

import java.util.Timer;

public class App {
  private static final Logger log = Logger.getLogger(App.class);



  public static void main(String[] args) {
    //ApiContextInitializer.init();
    Task task = new AvitoProductsTask();
    MainBot mainBot = new MainBot(task);
    mainBot.setToken("1724743407:AAGMX1kuvCZEz40hnC6m5IqTfPgdGLY-3_g");
    mainBot.connect();

  }
}
