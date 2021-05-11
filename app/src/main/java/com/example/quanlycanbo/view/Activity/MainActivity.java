package com.example.quanlycanbo.view.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.GiangVien;
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Adapter.CanBoAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    TextInputEditText textInput;
    TextView textView;
    private ArrayList<GiangVien> arrGiangVien;
    private  ArrayList<NhanVien> arrNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //ÁNH XẠ
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        textInput=findViewById(R.id.textInput);
        textView= findViewById(R.id.textView);

        tabLayout.addTab(tabLayout.newTab().setText("Giảng Viên"));
        tabLayout.addTab(tabLayout.newTab().setText("Nhân Viên"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new CanBoAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);

        textInput.setOnClickListener(this);
    }
    // TÍNH TỔNG LƯƠNG PHẢI TRẢ
    @Override
    public void onClick(View v) {
        arrGiangVien = getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_GIANG_VIEN);
        arrNhanVien = getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_NHAN_VIEN);
        int tongLuong = 0;
        for (GiangVien giangVien : arrGiangVien) {
            tongLuong += giangVien.tinhLuong();
        }
        for (NhanVien nhanVien : arrNhanVien) {
            tongLuong += nhanVien.tinhLuong();
        }
        int heSo = Integer.parseInt(textInput.getText().toString());
        tongLuong = tongLuong * heSo;
        textView.setText("Tổng Lương Phải Trả Trong " +textInput.getText().toString()+" Tháng Là: " + Integer.toString(tongLuong) + "đ");
    }
}