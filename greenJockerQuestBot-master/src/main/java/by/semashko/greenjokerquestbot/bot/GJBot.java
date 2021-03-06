package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.model.EngineSessionInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Slf4j
@Component
public class GJBot extends TelegramLongPollingBot {

    @Getter
    @Value("${bot.username}")
    private String botUsername;
    @Getter
    @Value("${bot.token}")
    private String botToken;

    private final UpdateReceiver updateReceiver;

    public GJBot(UpdateReceiver updateReceiver) {
        this.updateReceiver = updateReceiver;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        PartialBotApiMethod<? extends Serializable> responseToUser = updateReceiver.handleUpdate(update);

        if (responseToUser instanceof BotApiMethod){
            try{
                execute((BotApiMethod<? extends Serializable>)responseToUser);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
