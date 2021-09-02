package hrm.listener;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageHandlerProxy")
public class MessageHandlerProxy implements MessageHandler{
    @Autowired
    MessageHandlerReal messageHandler;

    @Override
    public void handler(Message message) {
        if(message == null){
            return;
        }
        if(Strings.isEmpty(message.getServiceName()) || Const.CURRENT_SERVICE_NAME.equals(message.getServiceName())){
            return;
        }
       if( ApiSynchronized.valueOf(message.apiType) ==   null ){
           return;
       }
       messageHandler.handler(message);
    }
}
