package com.example.quanlycanbo.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlycanbo.R;
import com.google.android.material.textfield.TextInputEditText;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText tNhap;
    private Button btnOK;
    public static final String SO_CAN_BO = "SOCANBO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        //ÁNH XẠ
        tNhap = findViewById(R.id.tNhap);
        btnOK = findViewById(R.id.btn_ok);

        btnOK.setOnClickListener(this);//bắt sự kiện
    }

    @Override//bắt sự kiện
    public void onClick(View v) {
        doLogin();
    }

    private void doLogin(){
        //NẾU TEXT RỖNG
        if(tNhap.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Hãy Nhập Số Cán Bộ!", Toast.LENGTH_SHORT).show();
            return;
        }
        //NẾU TEXT = 0
        if(Integer.parseInt(tNhap.getText().toString() ) == 0){
            Toast.makeText(this, "Số Cán Bộ Phải Lớn Hơn 0!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Intent intent = new Intent();
            intent.setClass(this, Activity2.class);//gọi tới activity2
            intent.putExtra(SO_CAN_BO, Integer.parseInt(tNhap.getText().toString()));//truyền dữ liệu
            startActivity(intent);//bắt đầu avtivity2
        }
    }
}
