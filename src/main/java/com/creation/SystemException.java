package com.creation;

import org.apache.log4j.Logger;

public class SystemException {
  public static void putError(Logger log, String text){
    log.error(text);
    System.err.println(text);
  }

  public static void putWarning(Logger log, String text){
    log.warn(text);
    System.err.println(text);
  }
}
