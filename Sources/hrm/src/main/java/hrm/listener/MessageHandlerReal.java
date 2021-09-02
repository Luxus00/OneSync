package hrm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("messageHandlerReal")
public class MessageHandlerReal implements MessageHandler {
    List<HandlerListener> handlerListeners;

    public MessageHandlerReal() {
        handlerListeners=new ArrayList<>();
    }
    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void handler(Message message) {
        ApiSynchronized apiSynchronized=ApiSynchronized.valueOf(message.apiType);
        Type classHandler=apiSynchronized.getClassHandler();
        Class<?> clazz = (Class<?>) classHandler;
        if(Objects.nonNull(classHandler)){
           HandlerListener handlerListener=(HandlerListener) applicationContext.getBean(clazz);
           handlerListener.execute(message.getData().toString());
        }
    }

    public void addHandlerListeners(HandlerListener handlerListener) {
        this.handlerListeners.add(handlerListener);
    }
}
