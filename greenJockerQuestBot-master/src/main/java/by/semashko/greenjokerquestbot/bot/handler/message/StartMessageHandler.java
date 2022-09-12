package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.keyboard.ReplyKeyboardMarkupBuilder;
import by.semashko.greenjokerquestbot.service.ReplyMessageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Setter
@Getter
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class StartMessageHandler implements MessageHandler {


    private  ReplyMessageService messageService;



    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.MENU);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        return message.getText().equals("/start")
                ? getMenu(message.getFrom().getId().toString()) : messageService.getTextMessage(message.getChatId().toString(),"Погнали");
    }

    private SendMessage getMenu(String chatId){
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Воспользуйтесь меню")
                .row()
                .button("Регистрация игры")
                .endRow()
                .row()
                .button("Помощь")
                .endRow()
                .build();
    }
}
