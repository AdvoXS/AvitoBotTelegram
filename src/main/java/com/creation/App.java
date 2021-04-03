package com.creation;


import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
  private static final Logger log = Logger.getLogger(App.class);
  final static int RECONNECT_PAUSE =10000;
  public static void main(String[] args) {
    Task task = new AvitoProductsTask();
    MainBot mainBot = new MainBot();
    mainBot.setUserName("AvitoVideocardsParserBot");
    mainBot.setToken("1724743407:AAGMX1kuvCZEz40hnC6m5IqTfPgdGLY-3_g");
    try {
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
      telegramBotsApi.registerBot(mainBot);
      SystemMessage.putDebug(log,"TelegramAPI started. Look for messages");

    } catch (TelegramApiRequestException e) {
      log.error("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. SystemMessage: " + e.getMessage());
      try {
        Thread.sleep(RECONNECT_PAUSE);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
        SystemMessage.putError(log, e.getMessage());
        return;
      }
    } catch (TelegramApiException e) {
      SystemMessage.putError(log, e.getMessage());
      e.printStackTrace();
    }
    SystemMessage.putDebug(log, "Bot started!");
  }
}
