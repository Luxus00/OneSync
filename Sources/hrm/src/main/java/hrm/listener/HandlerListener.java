package hrm.listener;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public  abstract class HandlerListener <T> {
    @Autowired
    MessageHandlerReal messageHandlerReal;

    @PostConstruct
    public void register(){
        messageHandlerReal.addHandlerListeners(this);
    }
     public void execute(String s) {
        Type genericType = this.getClass().getGenericSuperclass();
        Type actualType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        T t = new Gson().fromJson(s, actualType);
        execute(t);
    }
    protected abstract void execute(T t);
}