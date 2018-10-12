package OkHttp;

import java.util.Map;

import okhttp3.Headers;

public interface IResponse {
    /**
     * 响应码 * @return
     */
    int getCode();

    /**
     * 返回的数据 * @return
     */
    String getData();



    Headers getHeader();


}
