package OkHttp;

public class Test {


    public static void main(String[] args) throws Exception {
        IRequest request = new MyRequest("http://localhost:8080/add");//http://47.100.13.155:8080/TimeTable/userLogin
//        http://localhost:8080/userLogin
//        request.setHeader("", "");
        request.setBody("userId", "1640402200836");
        request.setBody("password", "1640402200836");
        IHttpClient mHttpClient = new MyOkHttpClient();
        mHttpClient.post(request, new OnResultListener<MyResponse>() {
            @Override
            public void onResult(MyResponse result) {
                System.out.println(result.getData());
            }

            @Override
            public void onError(Exception error) {
                System.out.println("超时");

            }
        });

    }
}