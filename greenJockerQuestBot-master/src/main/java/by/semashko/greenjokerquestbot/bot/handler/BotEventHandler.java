package by.semashko.greenjokerquestbot.bot.handler;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.exception.NoHandlerFoundException;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BotEventHandler {

    private final List<Handler<?>> messageHandlers;
    private final ReplyMessageService messageService;

    public BotApiMethod<?> handleTextMessageByEvent(Message message, BotEvent event) throws ExecutionException, InterruptedException {
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
