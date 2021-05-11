package com.example.quanlycanbo.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlycanbo.R;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNhap;
    private Button btnOK;
    public static final String SO_CAN_BO = "SOCANBO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        edtNhap = findViewById(R.id.edt_nhap);
        btnOK = findViewById(R.id.btn_ok);

        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        doLogin();
    }

    private void doLogin(){
        if(edtNhap.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Hãy Nhập Số Cán Bộ!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Integer.parseInt(edtNhap.getText().toString() ) == 0){
            Toast.makeText(this, "Số Cán Bộ Phải Lớn Hơn 0!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Intent intent = new Intent();
            intent.setClass(this, ActivityNhap.class);
            intent.putExtra(SO_CAN_BO, Integer.parseInt(edtNhap.getText().toString()));
            startActivity(intent);
        }
    }
}
