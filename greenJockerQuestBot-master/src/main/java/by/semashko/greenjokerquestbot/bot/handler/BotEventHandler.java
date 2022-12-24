package by.semashko.greenjokerquestbot.bot.handler;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.exception.NoHandlerFoundException;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
public class BotEventHandler {

    private final List<Handler<?>> messageHandlers;
    private final ReplyMessageService messageService;

    public BotEventHandler(List<Handler<?>> messageHandlers, ReplyMessageService messageService) {
        this.messageHandlers = messageHandlers;
        this.messageService = messageService;
    }

    public BotApiMethod<?> handleTextMessageByEvent(Message message, BotEvent event){
        Handler<?> messageHandler;
        try{
            messageHandler = messageHandlers.stream()
                    .filter(m -> m.canHandle(event))
                    .findAny()
                    .orElseThrow(NoHandlerFoundException::new);
        }catch (NoHandlerFoundException e){
            return messageService.getTextMessage(message.getChatId().toString(),"Ops") ;
        }
        return  messageHandler.handle(message);


    }
}
