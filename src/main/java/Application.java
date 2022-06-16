import bots.SimpleEchoBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        try {
            log.info("Registering telegram bot...");
            api.registerBot(new SimpleEchoBot());
        } catch (TelegramApiRequestException e) {
            log.error("Failed to register telegram bot: ", e);
        }
        log.info("Telegram bot is ready to accept updates from user");
    }
}
