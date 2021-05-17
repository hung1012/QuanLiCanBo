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

        getSupportActionBar().hide();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(2000);
                Intent intent = new Intent(SplashActivity.this, Activity1.class);
                startActivity(intent);
                finish();
            }
        });
        thread.start();
    }
}
