package com.example.instastory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Storieshere extends AppCompatActivity {

    public String username;
    public int id;
    public JSONObject gb;
    public JSONObject jo[];
    public JSONArray arr= new JSONArray();
    String urls[];
    public TextView tv ;
    public String urlprf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storieshere);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        getId();
    }
    private void getId(){
        new Thread(){
            public void run(){

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://instagram-stories1.p.rapidapi.com/v1/get_user_id?username="+username)
                        .get()
                        .addHeader("x-rapidapi-host", "instagram-stories1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "2716931fb1msh5693952f1b6a041p189cb2jsnf87ec135eaba")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    ResponseBody body = response.body();
                    String str = body.string();
                    JSONObject obj = new JSONObject(str);
                    id = obj.getInt("user_id");
                    String id_str = String.valueOf(id);


                    OkHttpClient client_st = new OkHttpClient();
                    Request request_st = new Request.Builder()
                            .url("https://instagram-stories1.p.rapidapi.com/v1/get_stories?userid="+id)
                            .get()
                            .addHeader("x-rapidapi-host", "instagram-stories1.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "2716931fb1msh5693952f1b6a041p189cb2jsnf87ec135eaba")
                            .build();

                    Response response_st = client_st
                            .newCall(request_st).execute();
                    ResponseBody body1 = response_st.body();
                    String str_here = body1.string();
                    gb = new JSONObject(str_here);

                    for (int i =0 ; i<arr.length();i++){

                        jo[i]= (JSONObject) arr.get(i);
                    }





                } catch (IOException | JSONException e) {
                    Log.d("errhere", "run: helel");
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i =0 ; i<arr.length();i++){


                            try {
                                Toast.makeText(Storieshere.this, ""+arr.get(i), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }



                    }
                });
            }
        }.start();
}}