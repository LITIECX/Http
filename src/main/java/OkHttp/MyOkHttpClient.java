package OkHttp;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class MyOkHttpClient implements IHttpClient {
    OkHttpClient mOkHttpClient = new OkHttpClient.Builder().build();
    MyResponse commonResponse = new MyResponse();

    @Override
    public void get(IRequest request, OnResultListener listener) {
        request.setMethod(IRequest.GET);
        Map<String, String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key, header.get(key));
        }
        builder.url(request.getUrl())
                .get();
        Request okRequest = builder.build();
        execute(okRequest, listener);  //发的请求
    }

    @Override
    public void post(IRequest request, OnResultListener listener) {
        request.setMethod(IRequest.POST);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, request.getBody());
        Map<String, String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key, header.get(key));
        }
        builder.url(request.getUrl())
                .post(requestBody);
        Request okRequest = builder.build();
        execute(okRequest, listener);
    }

    @Override
    public void upload_image_post(IRequest request, Map<String, Object> map, File file, OnResultListener listener) {
        request.setMethod(IRequest.POST);
        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            requestBody.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, file));
        }
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Map<String, String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key, header.get(key));
        }
        builder.url(request.getUrl())
                .post(requestBody.build());
        Request okRequest = builder.build();
        execute(okRequest, listener);
    }

    @Override
    public void delete(IRequest request, OnResultListener listener) {
        request.setMethod(IRequest.DELETE);
        Map<String, String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key, header.get(key));
        }
        builder.url(request.getUrl())
                .delete(null);
        Request okRequest = builder.build();
        execute(okRequest, listener);
    }

    @Override
    public void put(IRequest request, OnResultListener listener) {
        request.setMethod(IRequest.PUT);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, request.getBody().toString());
        Map<String, String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key, header.get(key));
        }
        builder.url(request.getUrl())
                .put(requestBody);
        Request okRequest = builder.build();
        execute(okRequest, listener);
    }


    private void execute(Request request, final OnResultListener listener) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                commonResponse.setCode(response.code());
                commonResponse.setData(response.body().string());
                commonResponse.setHeaders(response.headers());
                listener.onResult(commonResponse);
            }
        });

    }
}

