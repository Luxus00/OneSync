package com.vnpt.eoffice.controller.response;


import com.vnpt.eoffice.config.Translator;
import com.vnpt.eoffice.util.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class ResponseBody {
    private String code;
    private String message;
    private Object data;
    public static ResponseBody ofSuccess(Object data){
        ResponseBody responseData = new ResponseBody();
        responseData.setCode(Const.StatusCode.SUCCESS);
        responseData.setData(data);
        responseData.setMessage(Translator.translate(Const.Message.Common.SUCCESS));
        return responseData;
    }
    public static ResponseBody ofSuccess(){
        ResponseBody responseData = new ResponseBody();
        responseData.setCode(Const.StatusCode.SUCCESS);
        responseData.setMessage(Translator.translate(Const.Message.Common.SUCCESS));
        return responseData;
    }
    public static ResponseBody ofSuccess(Object data, String message){
        ResponseBody responseData = new ResponseBody();
        responseData.setCode(Const.StatusCode.SUCCESS);
        responseData.setMessage(Translator.translate(Const.Message.Common.SUCCESS));
        return responseData;
    }
    public static ResponseBody ofFailure(Object data){
        ResponseBody responseData = new ResponseBody();
        responseData.setCode(Const.StatusCode.FAILURE);
        responseData.setData(data);
        responseData.setMessage(Translator.translate(Const.Message.Common.FAILURE));
        return responseData;
    }
    public static ResponseBody ofFailure(Object data,String message){
        ResponseBody responseData = new ResponseBody();
        responseData.setCode(Const.StatusCode.FAILURE);
        responseData.setData(data);
        responseData.setMessage(Translator.translate(Const.Message.Common.FAILURE));
        return responseData;
    }
}
