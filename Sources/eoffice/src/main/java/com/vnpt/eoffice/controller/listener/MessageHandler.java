package com.vnpt.eoffice.controller.listener;

import org.springframework.context.ApplicationContextAware;

public interface MessageHandler  {
     void handler(Message message);
}
