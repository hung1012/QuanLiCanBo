package com.example.quanlycanbo.view.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.CanBo;
import com.example.quanlycanbo.model.GiaoVien;
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Adapter.CanBoAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    TextInputEditText textInput;
    TextView textView;
    private ArrayList<GiaoVien> arrGiaoVien;
    private  ArrayList<NhanVien> arrNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        arrGiaoVien = getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_GIAO_VIEN);
        arrNhanVien = getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_NHAN_VIEN);

        //ÁNH XẠ
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        textInput=findViewById(R.id.textInput);
        textView= findViewById(R.id.textView);

        tabLayout.addTab(tabLayout.newTab().setText("Giáo Viên"));
        tabLayout.addTab(tabLayout.newTab().setText("Nhân Viên"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new CanBoAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double tongLuong = 0;
                    ArrayList<CanBo> arrCanBo = new ArrayList<>();
                    for (GiaoVien giaoVien : arrGiaoVien) {
                        arrCanBo.add(giaoVien);
                    }
                    for (NhanVien nhanVien : arrNhanVien) {
                        arrCanBo.add(nhanVien);
                    }
                    for (CanBo canBo : arrCanBo){
                        tongLuong += canBo.tinhLuong();
                    }
                    int heSo = Integer.parseInt(textInput.getText().toString());
                    tongLuong = tongLuong * heSo;
                    textView.setText("Tổng Lương Phải Trả Trong " + textInput.getText().toString() + " Tháng Là: " + (int)tongLuong + "đ");
                }catch (Exception e){
                    textView.setText("Tổng Lương Phải Trả Trong 0 Tháng Là: 0đ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    // TÍNH TỔNG LƯƠNG PHẢI TRẢ
//    @Override
//    public void onClick(View v) {
//        double tongLuong = 0;
//        for (GiaoVien giaoVien : arrGiaoVien) {
//            tongLuong += giaoVien.tinhLuong();
//        }
//        for (NhanVien nhanVien : arrNhanVien) {
//            tongLuong += nhanVien.tinhLuong();
//        }
//        int heSo = Integer.parseInt(textInput.getText().toString());
//        tongLuong = tongLuong * heSo;
//        textView.setText("Tổng Lương Phải Trả Trong " +textInput.getText().toString()+" Tháng Là: " + Double.toString(tongLuong) + "đ");
//    }
}