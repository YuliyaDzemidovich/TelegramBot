package bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleEchoBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(SimpleEchoBot.class);

    public String getBotUsername() {
        return System.getenv("TELEGRAM_BOT_USERNAME");
    }

    public String getBotToken() {
        return System.getenv("TELEGRAM_BOT_TOKEN");
    }

    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        Message mes = update.getMessage();
        String userText = mes.getText();
        Long userId = mes.getChatId();
        String userFirstName = mes.getFrom().getFirstName();
        log.info ("Got message from user id {} and first name {}: {}", userId, userFirstName, userText);

        SendMessage answer = SendMessage.builder()
                .chatId(userId.toString())
                .text("Received text: " + userText)
                .build();
        try {
            sendApiMethod(answer);
        } catch (TelegramApiException e) {
            log.error("Failed sending response to the user: ", e);
        }
    }
}
