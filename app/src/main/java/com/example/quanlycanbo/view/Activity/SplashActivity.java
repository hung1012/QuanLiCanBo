package com.example.quanlycanbo.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlycanbo.R;
import static android.os.SystemClock.sleep;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1500);//chờ 1.5s
                Intent intent = new Intent(SplashActivity.this, Activity1.class);//gọi activity1
                startActivity(intent);//chuyển qua activity1
                finish();//kết thúc Activity
            }
        });
        thread.start();
    }
}
