package hrm.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KakaListener {
    @Autowired
    MessageHandlerProxy messageHandler;
    @KafkaListener(topics = "test_topic",groupId = "hrm")
    public void consumeMessage(String message){
        log.info(message);
        messageHandler.handler(new Gson().fromJson(message,Message.class));
    }
}
