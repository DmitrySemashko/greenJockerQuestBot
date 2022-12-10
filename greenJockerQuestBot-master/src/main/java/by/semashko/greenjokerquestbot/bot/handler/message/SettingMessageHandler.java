package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.keyboard.InlineKeyboardMarkupBuilder;
import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.exception.InvalidUrlException;
import by.semashko.greenjokerquestbot.infrastructure.service.AuthorizationServiceImpl;
import by.semashko.greenjokerquestbot.infrastructure.service.CheckGameState;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.util.UrlParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

@Slf4j
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@Getter
@Setter
@Component
public class SettingMessageHandler implements MessageHandler {


    private ReplyMessageService messageService;

    private AuthorizationServiceImpl authorizationService;

    private CheckGameState checkGameState;

    private static String domain = null;

    private static int gameId = 0;


    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.SETTING);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {

        if (message.getText().equals("Регистрация игры")) {
            return messageService.getTextMessage(message.getChatId().toString(), "Введите ссылку на игру");
        }
        try {
            if (domain == null) {
                domain = UrlParser.getDomain(message.getText());
                gameId = UrlParser.getIdGame(message.getText());
                log.info(Integer.toString(gameId));
                log.info(domain);
                return messageService.getTextMessage(message.getChatId().toString(), "Ввдите логин и пароль через пробел");
            }

            AuthorizationResponse response = authorizationService.authorization(domain, message.getText());

            if (response.getError() != 0) {
                return messageService.getTextMessage(message.getChatId().toString(), response.getMessage());
            }


            StateGame stateGame = checkGameState.getStateGame(domain, gameId);

            if (stateGame == StateGame.ACTIVE || stateGame == StateGame.GAME_NOT_START){
                return getButtonAddToChat(message.getChatId());
            }else {
                return messageService.getTextMessage(message.getChatId().toString(),stateGame.getDescription());
            }

        } catch (InvalidUrlException | IOException e) {
            return messageService.getTextMessage(message.getChatId().toString(), e.getMessage());
        }
    }


    private SendMessage getButtonAddToChat(Long chatId) {
        return InlineKeyboardMarkupBuilder.create(chatId)
                .setText("Добавить бота в чат")
                .row()
                .buttonWithURL("add to chat", "https://t.me/GreenJokerEn_bot?startgroup=XXXX")
                .endRow()
                .build();
    }
}
