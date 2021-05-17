package com.example.quanlycanbo.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;
import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.GiaoVien;
import com.example.quanlycanbo.model.NhanVien;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView textCanBo;
    private EditText textTen, textDonViCongTac, textHeSoLuong, textPhuCap,textSo;
    private Button button,buttonChuyển;
    private RadioGroup radioCanBo;              //
    private RadioButton radioGiaoVien;         //
    private RadioButton radioNhanVien;          //

    private String ten = "", donViCongTac = "";
    private double heSoLuong = -1, phuCap = -1;
    private int so = -1;

    private int soCanBo;
    private int count=1;

    public static final String LIST_GIAO_VIEN = "LISTGIAOVIEN";
    public static final String LIST_NHAN_VIEN = "LISTNHANVIEN";

    private ArrayList<GiaoVien> arrGiaoVien = new ArrayList<>();
    private ArrayList<NhanVien> arrNhanVien = new ArrayList<>();

    GiaoVien giaoVien = null;
    NhanVien nhanVien = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);

        getSupportActionBar().hide();

        //ÁNH XẠ
        textPhuCap = findViewById(R.id.textPhuCap);
        textTen = findViewById(R.id.textTen);
        textDonViCongTac = findViewById(R.id.textDonViCongTac);
        textHeSoLuong = findViewById(R.id.textHeSoLuong);
        textSo = findViewById(R.id.textSo);

        textCanBo = findViewById(R.id.tv_canbo);

        button = findViewById(R.id.button);
        buttonChuyển = findViewById(R.id.button1);

        radioCanBo = findViewById(R.id.radioGroup_canBo);
        radioGiaoVien = findViewById(R.id.radioButton_giaoVien);
        radioNhanVien = findViewById(R.id.radioButton_nhanVien);

        CompoundButton.OnCheckedChangeListener listenerRadio = (buttonView, isChecked) -> {
        };

        radioGiaoVien.setOnCheckedChangeListener(listenerRadio);
        radioNhanVien.setOnCheckedChangeListener(listenerRadio);
        radioNhanVien.setOnClickListener(this);
        radioGiaoVien.setOnClickListener(this);
        radioGiaoVien.setChecked(true);

        button.setOnClickListener(this);
        buttonChuyển.setOnClickListener(this);

        buttonChuyển.setVisibility(View.GONE);
        //nhận số cán bộ từ Activity1
        soCanBo = getIntent().getIntExtra(Activity1.SO_CAN_BO,0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.radioButton_nhanVien:
                textSo.setHint("Số Ngày Công");
                break;
            case R.id.radioButton_giaoVien:
                textSo.setHint("Số Tiết Dạy");
                break;
            case R.id.button:
                count++;
                if(radioGiaoVien.isChecked()) {
                    try {
                        getInf();
                        giaoVien = new GiaoVien(ten, donViCongTac, heSoLuong, phuCap, so);
                        arrGiaoVien.add(giaoVien);
                    }catch(Exception e){
                        Toast.makeText(this, "Hãy Nhập Đầy Đủ Thông Tin Giáo Viên", Toast.LENGTH_SHORT).show();
                        count--;
                        return;
                    }
                }
                else if(radioNhanVien.isChecked()){
                    try {
                        getInf();
                        nhanVien = new NhanVien(ten, donViCongTac, heSoLuong, phuCap, so);
                        arrNhanVien.add(nhanVien);
                    }catch(Exception e){
                        Toast.makeText(this, "Hãy Nhập Đầy Đủ Thông Tin Nhân Viên", Toast.LENGTH_SHORT).show();
                        count--;
                        return;
                    }
                }
                if (count > soCanBo) {
                    textCanBo.setText("Đã Nhập Xong Các Cán Bộ");
                    makeInvisible();
                    break;
                }
                else {
                    textCanBo.setText("Cán Bộ " + count);
                    break;
                }
            case R.id.button1:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                //TRUYỀN 2 arr SANG CanBoList
                intent.putExtra(LIST_GIAO_VIEN, arrGiaoVien);
                intent.putExtra(LIST_NHAN_VIEN, arrNhanVien);
                startActivity(intent);
            default:
                break;
        }
    }
    public void getInf() {
        ten = textTen.getText().toString();
        heSoLuong = Double.parseDouble(textHeSoLuong.getText().toString());
        donViCongTac = textDonViCongTac.getText().toString();
        phuCap = Double.parseDouble(textPhuCap.getText().toString());
        so = Integer.parseInt(textSo.getText().toString());
        textTen.setText("");
        textDonViCongTac.setText("");
        textHeSoLuong.setText("");
        textPhuCap.setText("");
        textSo.setText("");
    }
    public void makeInvisible(){
        button.setVisibility(View.INVISIBLE);
        radioCanBo.setVisibility(View.INVISIBLE);
        textTen.setVisibility(View.INVISIBLE);
        textDonViCongTac.setVisibility(View.INVISIBLE);
        textHeSoLuong.setVisibility(View.INVISIBLE);
        textPhuCap.setVisibility(View.INVISIBLE);
        textSo.setVisibility(View.INVISIBLE);
        buttonChuyển.setVisibility(View.VISIBLE);
    }
}
