package com.vnpt.eoffice.controller.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KakaListener {
    @Autowired
    MessageHandlerProxy messageHandler;
    @KafkaListener(topics = "test_topic",groupId = "eoffice")
    public void consumeMessage(String message){
        log.info(message);
        messageHandler.handler(new Gson().fromJson(message,Message.class));
    }
}
