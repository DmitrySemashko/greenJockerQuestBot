package by.semashko.greenjokerquestbot.bot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Component
@Data
public class GJBot extends TelegramLongPollingBot {


    @Value("${bot.username}")
    private String botUsername;

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

    @SneakyThrows
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
