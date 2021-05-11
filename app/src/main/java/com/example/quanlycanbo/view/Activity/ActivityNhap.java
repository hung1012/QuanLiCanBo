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
import android.widget.TextView;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.GiangVien;
import com.example.quanlycanbo.model.NhanVien;

import java.util.ArrayList;

public class ActivityNhap extends AppCompatActivity implements View.OnClickListener {
    private TextView canBo;
    private EditText textTen, textDonViCongTac, textHeSoLuong, textPhuCap,textSo;
    private String ten = "", donViCongTac = "";
    private int heSoLuong = -1, phuCap = -1, so = -1;
    private Button button,buttonChuyển;
    private RadioGroup radioCanBo;              //
    private RadioButton radioGiangVien;         //
    private RadioButton radioNhanVien;          //
    private int soCanBo;
    private int count=1;
    public static final String LIST_GIANG_VIEN = "LISTGIANGVIEN";
    public static final String LIST_NHAN_VIEN = "LISTNHANVIEN";

    private ArrayList<GiangVien> arrGiangVien = new ArrayList<>();
    private ArrayList<NhanVien> arrNhanVien = new ArrayList<>();

    GiangVien giangVien = null;
    NhanVien nhanVien = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);

        //ÁNH XẠ
        textPhuCap = findViewById(R.id.textPhuCap);
        textTen = findViewById(R.id.textTen);
        textDonViCongTac = findViewById(R.id.textDonViCongTac);
        textHeSoLuong = findViewById(R.id.textHeSoLuong);
        textSo = findViewById(R.id.textSo);

        canBo = findViewById(R.id.tv_canbo);

        button = findViewById(R.id.button);
        buttonChuyển = findViewById(R.id.button1);

        radioCanBo = findViewById(R.id.radioGroup_canBo);
        radioGiangVien = findViewById(R.id.radioButton_giangVien);
        radioNhanVien = findViewById(R.id.radioButton_nhanVien);

        CompoundButton.OnCheckedChangeListener listenerRadio = (buttonView, isChecked) -> {
        };

        radioGiangVien.setOnCheckedChangeListener(listenerRadio);
        radioNhanVien.setOnCheckedChangeListener(listenerRadio);
        radioNhanVien.setOnClickListener(this);
        radioGiangVien.setOnClickListener(this);
        radioGiangVien.setChecked(true);

        button.setOnClickListener(this);
        buttonChuyển.setOnClickListener(this);

        buttonChuyển.setVisibility(View.GONE);
        //nhận số cán bộ từ MainActivity
        soCanBo = getIntent().getIntExtra(Activity1.SO_CAN_BO,0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.radioButton_nhanVien:
                textSo.setHint("Số Ngày Công");
                break;
            case R.id.radioButton_giangVien:
                textSo.setHint("Số Tiết Dạy");
                break;
            case R.id.button:
                count++;
                if(radioGiangVien.isChecked()) {
                    getSoLieu();
                    giangVien = new GiangVien(ten, donViCongTac, heSoLuong, phuCap, so);
                    arrGiangVien.add(giangVien);
                    if (count > soCanBo) {
                        canBo.setText("Đã Nhập Xong Các Cán Bộ");
                        makeInvisible();
                        break;
                    }
                    else {
                        canBo.setText("Cán Bộ " + count);
                        break;
                    }
                }
                if(radioNhanVien.isChecked()){
                    getSoLieu();
                    nhanVien = new NhanVien(ten, donViCongTac, heSoLuong, phuCap, so);
                    arrNhanVien.add(nhanVien);
                    if (count > soCanBo) {
                        canBo.setText("Đã Nhập Xong Các Cán Bộ");
                        makeInvisible();
                        break;
                    }
                    else {
                        canBo.setText("Cán Bộ " + count);
                        break;
                    }
                }
            case R.id.button1:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                //TRUYỀN 2 arr SANG CanBoList
                intent.putExtra(LIST_GIANG_VIEN, arrGiangVien);
                intent.putExtra(LIST_NHAN_VIEN, arrNhanVien);
                startActivity(intent);
            default:
                break;
        }
    }
    public void getSoLieu(){
            ten = textTen.getText().toString();
            heSoLuong = Integer.parseInt(textHeSoLuong.getText().toString());
            donViCongTac = textDonViCongTac.getText().toString();
            phuCap = Integer.parseInt(textPhuCap.getText().toString());
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
        buttonChuyển.setVisibility(View.VISIBLE);
        textTen.setVisibility(View.INVISIBLE);
        textDonViCongTac.setVisibility(View.INVISIBLE);
        textHeSoLuong.setVisibility(View.INVISIBLE);
        textPhuCap.setVisibility(View.INVISIBLE);
        textSo.setVisibility(View.INVISIBLE);
    }
}
