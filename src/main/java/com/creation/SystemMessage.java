package com.creation;

import org.apache.log4j.Logger;

public class SystemMessage {
  public static void putError(Logger log, String text){
    log.error(text);
    System.err.println(text);
  }

  public static void putWarning(Logger log, String text){
    log.warn(text);
    System.err.println(text);
  }

  public static void putDebug(Logger log, String text){
    log.debug(text);
    System.out.println(text);
  }
}
