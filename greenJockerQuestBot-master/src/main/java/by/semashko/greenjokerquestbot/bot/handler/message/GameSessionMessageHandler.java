package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.handler.Handler;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.service.GameService;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import by.semashko.greenjokerquestbot.infrastructure.service.impl.GameSessionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import static by.semashko.greenjokerquestbot.util.StringConstants.GAME_TRACKING_ENABLED;

@Component
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameSessionMessageHandler implements Handler<Message> {

    private UserService userService;
    private GameService gameService;
    private ReplyMessageService messageService;
    private GameSessionService gameSessionService;

    private static Long telegramId;
    private static Long telegramUserId;

    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.START_GAME_SESSION);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        telegramId = message.getChatId();
        telegramUserId = message.getFrom().getId();
        if (message.getText().equals("/start@GreenJokerEn_bot") || message.getText().equals("/start@GreenJokerEn_bot 1111")) {
            gameSessionService.setTelegramChatId(telegramUserId);
            if (gameSessionService.isGameActive()) {
                if (gameSessionService.getModel().getEngineAction().getLevelAction().isCorrect()) {
                    return messageService.getTextMessage(telegramId.toString(), gameSessionService.getTask().getText());
                }
                return messageService.getTextMessage(telegramId.toString(), gameSessionService.getTask().getText());
            }
            return messageService.getTextMessage(telegramId.toString(), GAME_TRACKING_ENABLED);
        }
        if (!gameSessionService.getLevel().isPassed()) {
            Game game = userService.getByChatId(telegramUserId.toString()).getGame();
            return messageService.getTextMessage(telegramId.toString(), gameSessionService.sendCode(Integer.parseInt(game.getGameId()), game.getDomain(), String.valueOf(gameSessionService.getLevel().getLevelId()), gameSessionService.getLevel().getNumber(), message.getText()));
        }
        return messageService.getTextMessage(telegramId.toString(), GAME_TRACKING_ENABLED);
    }

}