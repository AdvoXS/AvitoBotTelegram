package com.creation.command;

import com.creation.SystemMessage;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract public class ServiceCommand extends BotCommand {
  private static final Logger log = Logger.getLogger(ServiceCommand.class);

  ServiceCommand(String identifier, String description) {
    super(identifier, description);
  }

  void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
    SendMessage message = new SendMessage();
    message.enableMarkdown(true);
    message.setChatId(chatId.toString());
    message.setText(text);
    try {
      absSender.execute(message);
    } catch (TelegramApiException e) {
      SystemMessage.putError(log, e.getMessage());
      //логируем сбой Telegram Bot API, используя commandName и userName
    }
  }
}
