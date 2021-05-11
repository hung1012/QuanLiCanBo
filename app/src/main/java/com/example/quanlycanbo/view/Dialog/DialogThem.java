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
import com.example.quanlycanbo.model.GiangVien;
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Activity.ActivityNhap;
import com.example.quanlycanbo.view.Fragment.GiangVienFragment;

import java.util.ArrayList;

public class DialogThem extends AppCompatDialogFragment {
    private EditText Hoten;
    private EditText Donvi;
    private EditText Heso;
    private EditText Phucap;
    private EditText So;
    private ArrayList arrGiangVien, arrNhanVien;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        //NHẬN arrGiangVien, arrNhanVien
        arrGiangVien = getActivity().getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_GIANG_VIEN);
        arrNhanVien = getActivity().getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_NHAN_VIEN);

        //ÁNH XẠ
        Hoten = view.findViewById(R.id.textHoTen);
        Donvi = view.findViewById(R.id.textDViCongTac);
        Heso = view.findViewById(R.id.textHSoLuong);
        Phucap = view.findViewById(R.id.textPhCap);
        So = view.findViewById(R.id.textSo);

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
                        boolean themGiangVien = GiangVienFragment.themGiangVien;
                        if(themGiangVien) {
                            arrGiangVien.add(new GiangVien(Hoten.getText().toString(),
                                    Donvi.getText().toString(),
                                    Integer.parseInt(Heso.getText().toString()),
                                    Integer.parseInt(Phucap.getText().toString()),
                                    Integer.parseInt(So.getText().toString())));
                            Toast.makeText(getActivity(), "Đã Thêm Một Giảng Viên Vào Danh Sách", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            arrNhanVien.add(new NhanVien(Hoten.getText().toString(),
                                    Donvi.getText().toString(),
                                    Integer.parseInt(Heso.getText().toString()),
                                    Integer.parseInt(Phucap.getText().toString()),
                                    Integer.parseInt(So.getText().toString())));
                            Toast.makeText(getActivity(), "Đã Thêm Một Nhân Viên Vào Danh Sách", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return builder.create();
    }
}
