package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.handler.Handler;
import by.semashko.greenjokerquestbot.infrastructure.service.GameService;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@NoArgsConstructor
@Getter
@Setter
public class GameSessionMessageHandler implements Handler<Message> {

    private UserService userService;
    private GameService gameService;
    private ReplyMessageService messageService;
    private WatchEngine watchEngine;


    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.START_GAME_SESSION);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        if (message.getText().equals("/start@GreenJokerEn_bot")){
            Long telegramId = message.getFrom().getId();
            watchEngine.setTelegramChatId(telegramId);

        }
        return null;
    }

    private boolean isGameExist(Long telegramID){
        return userService.getByChatId(telegramID.toString()) != null;
    }
}
