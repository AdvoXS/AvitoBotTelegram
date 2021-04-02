package com.creation;

import com.creation.filter.url.RegionFilter;
import com.creation.filter.url.SortProductsFilter;
import com.creation.filter.url.UrlFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;
import java.util.List;
import java.util.Timer;

@AllArgsConstructor
@NoArgsConstructor
public class MainBot extends TelegramLongPollingBot {

  private static final Logger log = Logger.getLogger(MainBot.class);
  //TODO:TEST
  private static String url = "https://www.avito.ru/rossiya/tovary_dlya_kompyutera/komplektuyuschie/videokarty?s=104";
  final int RECONNECT_PAUSE =10000;

  @Setter
  @Getter
  String userName;
  @Setter
  @Getter
  String token;

  @Getter
  Task task;
  public MainBot(Task task) {
    this.task = task;
  }

  @Getter


  String chatId;
  public void onUpdatesReceived(List<Update> updates) {


  }

  @Override
  public void onUpdateReceived(Update update) {
    SystemMessage.putDebug(log, "Bot received message! Text: "+ update.getMessage());
    chatId = String.valueOf(update.getMessage().getChatId());
    String inputText = update.getMessage().getText();
    if (inputText.startsWith("/start")) {
      SendMessage message = new SendMessage();
      message.setChatId(chatId);
      message.setText("Привет. Начинаем парсить авито!");
      try {
        execute(message);
      } catch (TelegramApiException e) {
        SystemMessage.putError(log, e.getMessage());
        e.printStackTrace();
      }
    }
    //TODO:TEST
    UrlFilter filter = new RegionFilter();
    Timer timer = new Timer();

    task.setActive(true);
    //task.setUrl(filter.filterUrl(url, "Vologda")); // d = 1 с авито доставкой, pmax = 20000 макс цена, s=104 фильтр по дате
    //filter = new PriceMaxFilter();
    task.setUrl(filter.filterUrl(url,"20000"));
    filter = new SortProductsFilter();
    task.setUrl(filter.filterUrl(url,SortProductsFilter.ValueFilter.DEFAULT.getValue()));
    timer.schedule(task,1000, 100000);
  }
  public String getBotUsername() {
    return userName;
  }

  public String getBotToken() {
    return token;
  }

  public void connect(){
    try {
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
      telegramBotsApi.registerBot(this);
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
      connect();
    } catch (TelegramApiException e) {
      SystemMessage.putError(log, e.getMessage());
      e.printStackTrace();
    }
  }

  public void sendPhoto(String chatId, String text, InputStream is){
    SendPhoto message = new SendPhoto();
    message.setCaption(text);
    message.setParseMode(ParseMode.MARKDOWN);
    InputFile inputFile = new InputFile(is,is.toString());
    message.setPhoto(inputFile);
    message.setChatId(chatId);
    try {
      execute(message);
    } catch (TelegramApiException e) {
      SystemMessage.putError(log, e.getMessage());
      e.printStackTrace();
    }
  }


}
