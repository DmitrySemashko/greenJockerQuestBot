package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.keyboard.InlineKeyboardMarkupBuilder;
import by.semashko.greenjokerquestbot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SettingMessageHandler implements MessageHandler {

    private final ReplyMessageService messageService;

    public SettingMessageHandler(ReplyMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.SETTING);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {



        if (message.getText().equals("Регистрация игры")){
            return messageService.getTextMessage(message.getChatId().toString(), "Введите ссылку на игру");
        }

       return null;

    }


    private SendMessage getButtonAddToChat(Long chatId){
        return InlineKeyboardMarkupBuilder.create(chatId)
                .setText("add to chat")
                .row()
                .buttonWithURL("add to chat", "https://t.me/GreenJokerEn_bot?startgroup=XXXX")
                .endRow()
                .build();
    }
}
