package OkHttp;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MyRequest implements IRequest {
    private String method = POST;
    private String url;
    private Map<String, String> header;
    private Map<String, Object> body;

    public MyRequest(String url) {
        this.url = url;
        header = new HashMap<String, String>();
        body = new HashMap<String, Object>();
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setHeader(String key, String value) {
        header.put(key, value);
    }

    @Override
    public void setBody(String key, Object value) {
        body.put(key, value);
    }

    @Override
    public String getUrl() {
        if (GET.equals(method)) {
            for (String key : body.keySet()) {
                url = url.replace("${" + key + "}", body.get(key).toString());
            }
        } return url;
    }

    @Override
    public Map<String, String> getHeader() {
        return header;
    }

    @Override
    public String getBody() {
        if (body != null) {
            return new Gson().toJson(this.body, HashMap.class);
        } else {
            return "{}";
        }
    }
}


