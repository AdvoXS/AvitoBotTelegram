package com.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class AvitoProductsTask extends Task {
  Document doc;
  @Getter
  @Setter
  MainBot bot;

  public void run() {
    if (isActive) {
      try {
        doc = Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5").get();
        Elements listTitles = doc.select("[itemtype='http://schema.org/Product']");
        HashMap<String, ArrayList<Object>> listHashMap = getMapInfo(listTitles);
        for (Map.Entry<String, ArrayList<Object>> entry : listHashMap.entrySet()) {
          if (bot.chatId != null)
            bot.sendPhoto(bot.getChatId(), entry.getValue().get(0) + " " + entry.getValue().get(1) + "\u20BD\n" + entry.getKey(), (InputStream) entry.getValue().get(2));
          //TODO:TEST
          return;
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private HashMap<String, ArrayList<Object>> getMapInfo(Elements el) {
    HashMap<String, ArrayList<Object>> elementsInfo = new HashMap<>();
    for (Element element : el) {
      ArrayList<Object> list = new ArrayList<>();
      list.add(element.select("[data-marker='item-title']").select("[itemprop='name']").text());
      list.add(element.select("[itemprop='price']").attr("content"));
      list.add(downloadImage(element.select("[itemprop='image']").attr("src")));
      elementsInfo.put(element.select("[data-marker='item-title']").attr("href"), list);
    }
    return elementsInfo;
  }

  private static InputStream downloadImage(String strImageURL) {
    String strImageName =
        strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

    System.out.println("Downloading: " + strImageName + ", from: " + strImageURL);

    try {
      URL urlImage = new URL(strImageURL);
      return urlImage.openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

