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
    private RadioGroup radioCanBo;
    private RadioButton radioGiaoVien, radioNhanVien;

    private String ten = "", donViCongTac = "";
    private double heSoLuong = -1, phuCap = -1;
    private int so = -1;

    private int soCanBo;
    private int count=1;//đếm số cán bộ

    public static final String LIST_GIAO_VIEN = "LISTGIAOVIEN";
    public static final String LIST_NHAN_VIEN = "LISTNHANVIEN";
    //Tạo 2 ArrayList<>
    private ArrayList<GiaoVien> arrGiaoVien = new ArrayList<>();
    private ArrayList<NhanVien> arrNhanVien = new ArrayList<>();

    GiaoVien giaoVien = null;
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
        radioGiaoVien.setChecked(true);//mặc định set radioGiaoVien checked

        button.setOnClickListener(this);
        buttonChuyển.setOnClickListener(this);

        buttonChuyển.setVisibility(View.GONE);

        //nhận số cán bộ từ Activity1
        soCanBo = getIntent().getIntExtra(Activity1.SO_CAN_BO,0);
    }

    @Override//BẮT SỰ KIỆN onClick
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.radioButton_nhanVien:
                textSo.setHint("Số Ngày Công");//chuyển hint textSo nếu đang là Số Tiết Dạy
                break;
            case R.id.radioButton_giaoVien:
                textSo.setHint("Số Tiết Dạy");//
                break;
            case R.id.button:
                count++;//tăng số CanBo đã đếm lên 1
                if(radioGiaoVien.isChecked()) {//Nếu GiaoVien đc chọn
                    try {
                        getInf();//lấy thông tin
                        giaoVien = new GiaoVien(ten, donViCongTac, heSoLuong, phuCap, so);
                        arrGiaoVien.add(giaoVien);//thêm GiaoVien vào cuối arrGiaoVien
                    }catch(Exception e){
                        Toast.makeText(this, "Hãy Nhập Đầy Đủ Thông Tin Giáo Viên", Toast.LENGTH_SHORT).show();
                        count--;//nếu lỗi, giảm số cán bộ đã đếm đi 1
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
                if (count > soCanBo) {//nếu đếm đủ số cán bộ
                    textCanBo.setText("Đã Nhập Xong Các Cán Bộ");
                    makeInvisible();//ẩn các text, button k cần đến
                    break;
                }
                else {
                    textCanBo.setText("Cán Bộ " + count);
                    break;
                }
            case R.id.button1://gọi đến và bắt đầu MainActivity
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                //TRUYỀN 2 arr SANG MainActivity
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
        //hiện button chuyển màn
        buttonChuyển.setVisibility(View.VISIBLE);
    }
}
