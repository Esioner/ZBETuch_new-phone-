package com.youli.zbetuch.jingan.utils;


import android.util.Log;

import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttpUtils {

    public static final String BaseUrl="http://web.youli.pw:89";
    private static final String TAG = "asdasdasd";
    static OkHttpClient okHttpClient = null;

    //懒汉
    private static synchronized OkHttpClient getInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    /**
     * OKHttp 同步 Get
     *
     * @param url 请求网址
     * @return 获取到数据返回Response，若未获取到数据返回null
     */
    public static Response okHttpLogin(String url) {
        getInstance();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    /**
     * @return
     * 根据 cookie 请求数据或提交数据
     *
     * @param url
     */
    public static Response okHttpGet(String url) {
        getInstance();
        String cookies = SharedPreferencesUtils.getString("cookies");
        Log.d(TAG, "okHttpGet: "+ cookies);
        Request request;
        if (!cookies.isEmpty()) {
            request = new Request.Builder().addHeader("cookie", cookies).url(url).build();
        } else {
            request = new Request.Builder().url(url).build();
        }
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 阻塞 Post
     *
     * @param url      url
     * @param userName 用户名
     * @param psd      密码
     * @return Response
     */
    public Response okHttpPost(String url, String userName, String psd) {
        getInstance();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", psd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    //失业无业的调查提交
    public static Response okHttpPost(String url,String ID,String DQYX,String MQZK,String DATE,String MARK){
        getInstance();
        String cookies = SharedPreferencesUtils.getString("cookies");
        RequestBody requestBody=new FormBody.Builder()
                .add("ID",ID).add("NEW_DQYX",DQYX).add("NEW_MQZK",MQZK)
                .add("SURVEY_DATE",DATE).add("MARK",MARK)
                .build();
        Request request=new Request.Builder().url(url)
                .post(requestBody).addHeader("cookie",cookies).build();
        Response response;

        try {
            response=okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    return  response;
    }

    //服务记录的新增（ServiceReFramgent）
//    http://web.youli.pw:89/Json/Set_Sfz_Service.aspx?ID=0&SFZ=310108198004026642
//            &STAFF=2&SERVICE_TIME=2017-8-18&TYPE=1&MARK=测试
    public static Response okHttpPost(String url,String ID,String SFZ,String STAFF,String SERVICE_TIME,String TYPE,String MARK){

        getInstance();
        String cookies= SharedPreferencesUtils.getString("cookies");
        RequestBody requestBody=new FormBody.Builder().add("ID",ID)
                .add("SFZ",SFZ).add("STAFF",STAFF).add("SERVICE_TIME",SERVICE_TIME)
                .add("TYPE",TYPE).add("MARK",MARK).build();

        Request request=new Request.Builder().url(url)
        .post(requestBody).addHeader("cookie",cookies).build();
        Response response;

        try {
            response=okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response;
    }

//服务记录的删除
    //http://web.youli.pw:89/Json/Set_Sfz_Service.aspx?ID=52&del=true

    public static Response okHttpPostDelServiceRe(String url,String ID,String del){

        getInstance();
        String cookies=SharedPreferencesUtils.getString("cookies");
        RequestBody requestBody=new FormBody.Builder().add("ID",ID)
                .add("del",del).build();

        Request request=new Request.Builder().url(url).post(requestBody)
                .addHeader("cookie",cookies).build();

        Response response;

        try {
            response=okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return response;
    }

    //异步Get
    public static void okHttpAsynGet(String url, Callback callback) {
        getInstance();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;

        okHttpClient.newCall(request).enqueue(callback);

    }

    //异步 Post
    public static void okHttpAsyncPost(String url, String userName, String psd, Callback callback) {
        getInstance();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", psd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
