package com.mph.ghost.ghost1.dao;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mph.ghost.ghost1.aframework.security.Cipher;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @author 马鹏昊
 * @date {date}
 * @des
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class MainDao {



   public JSONObject getRequestData(){
       try {

           OkHttpClient okHttpClient = new OkHttpClient();
           RequestBody body = new FormEncodingBuilder().add("json",
                   "").build();
            String url = "在这填地址";
           Request request = new Request.Builder().url(url).post(body).build();
           Response response = okHttpClient.newCall(request).execute();
           String responseString = response.body().string();
           String realData = Cipher.AESDecrypt(responseString);
           if (response.isSuccessful()) {
                return JSON.parseObject(realData);
           } else {
              return new JSONObject();
           }

       } catch (JSONException e1) {
//           e1.printStackTrace();
           return new JSONObject();
       } catch (Exception e2) {
//           e2.printStackTrace();
           return new JSONObject();
       }
   }

}
