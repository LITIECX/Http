package OkHttp;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;

public class MyResponse implements IResponse {
    public static final int STATE_UNKNOW_ERROR = 100001;
    public static int STATE_OK = 200;
    private int code;
    private String data;
    private Headers headers;

    @Override
    public String getData() {
        return data;
    }

    @Override
    public Headers getHeader() {
        return headers;
    }


    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void  setHeaders(Headers headers){
        this.headers = headers;
    }
}

