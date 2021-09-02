package com.vnpt.eoffice.controller.listener;

import com.google.gson.Gson;
import com.vnpt.eoffice.util.Const;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
