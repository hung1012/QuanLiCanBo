package com.example.quanlycanbo.view.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.GiaoVien;
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Activity.Activity2;
import com.example.quanlycanbo.view.Fragment.GiaoVienFragment;

import java.util.ArrayList;

public class DialogThem extends AppCompatDialogFragment {
    private EditText Hoten;
    private EditText Donvi;
    private EditText Heso;
    private EditText Phucap;
    private EditText So;
    private ArrayList arrGiangVien, arrNhanVien;
    boolean themGiangVien = GiaoVienFragment.themGiaoVien;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        //NHẬN arrGiangVien, arrNhanVien
        arrGiangVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_GIAO_VIEN);
        arrNhanVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_NHAN_VIEN);

        //ÁNH XẠ
        Hoten = view.findViewById(R.id.textHoTen);
        Donvi = view.findViewById(R.id.textDViCongTac);
        Heso = view.findViewById(R.id.textHSoLuong);
        Phucap = view.findViewById(R.id.textPhCap);
        So = view.findViewById(R.id.textSo);

        if(!themGiangVien){
            So.setHint("Số Ngày Công");
        }

        //
        builder.setView(view)
                .setTitle("THÊM")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(themGiangVien) {
                            try {
                                arrGiangVien.add(new GiaoVien(Hoten.getText().toString(),
                                        Donvi.getText().toString(),
                                        Double.parseDouble(Heso.getText().toString()),
                                        Double.parseDouble(Phucap.getText().toString()),
                                        Integer.parseInt(So.getText().toString())));
                                Toast.makeText(getActivity(), "Đã Thêm Một Giáo Viên Vào Danh Sách", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(getActivity(), "Hãy Nhập Đầy Đủ", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        else{
                            try {
                                arrNhanVien.add(new NhanVien(Hoten.getText().toString(),
                                        Donvi.getText().toString(),
                                        Double.parseDouble(Heso.getText().toString()),
                                        Double.parseDouble(Phucap.getText().toString()),
                                        Integer.parseInt(So.getText().toString())));
                                Toast.makeText(getActivity(), "Đã Thêm Một Nhân Viên Vào Danh Sách", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(getActivity(), "Hãy Nhập Đầy Đủ", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                });


        return builder.create();
    }
}
