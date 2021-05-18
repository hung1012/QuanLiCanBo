package com.example.quanlycanbo.view.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.CanBo;
import com.example.quanlycanbo.model.GiaoVien;
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Adapter.ViewPagerAdapter;
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

        //Nhận 2 ArrayList từ Activity2
        arrGiaoVien = getIntent().getParcelableArrayListExtra(Activity2.LIST_GIAO_VIEN);
        arrNhanVien = getIntent().getParcelableArrayListExtra(Activity2.LIST_NHAN_VIEN);

        //ÁNH XẠ
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        textInput=findViewById(R.id.textInput);
        textView= findViewById(R.id.textView);

        tabLayout.addTab(tabLayout.newTab().setText("Giáo Viên"));
        tabLayout.addTab(tabLayout.newTab().setText("Nhân Viên"));//2 tab
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);//lk tabLayout và viewPager

        //TÍNH TỔNG LƯƠNG PHẢI TRẢ
        //BẮT SỰ KIỆN THAY ĐỔI textInput
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
                    int heSo = Integer.parseInt(textInput.getText().toString());//ng dùng nhập
                    tongLuong = tongLuong * heSo;
                    textView.setText("Tổng Lương Phải Trả Trong " + textInput.getText().toString() + " Tháng Là: " + (int)tongLuong + "đ");
                }catch (Exception e){
                    textView.setText("Tổng Lương Phải Trả Trong 0 Tháng Là: 0đ");//nếu text rỗng
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}