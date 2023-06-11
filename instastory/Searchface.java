package com.example.instastory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Searchface extends AppCompatActivity {

    public EditText search;
    public Button getStory;
    public String username;
    LottieAnimationView lotte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchface);
        search  = findViewById(R.id.edit_search);
        getStory = findViewById(R.id.butt_search);
        lotte = findViewById(R.id.lotte);
        lotte.setVisibility(View.GONE);
        getStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search.getText().toString().isEmpty()){
                    Toast.makeText(Searchface.this, "Empty", Toast.LENGTH_SHORT).show();
                }else{
                    username = search.getText().toString().toLowerCase();
                    Toast.makeText(Searchface.this, ""+username, Toast.LENGTH_SHORT).show();
                    lotte.setVisibility(View.VISIBLE);

                    Thread td = new Thread(){
                        public void run(){
                            try {
                                sleep(2000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Intent intent = new Intent(Searchface.this,Storieshere.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                                finish();
                            }

                        }
                    };td.start();

                }
            }
        });


    }
}