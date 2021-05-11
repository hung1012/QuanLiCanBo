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
import com.example.quanlycanbo.view.Fragment.NhanVienFragment;

import java.util.ArrayList;

public class DialogSua extends AppCompatDialogFragment {
    private EditText Hoten;
    private EditText Donvi;
    private EditText Heso;
    private EditText Phucap;
    private EditText So;
    private ArrayList<GiangVien> arrGiangVien;
    private ArrayList<NhanVien> arrNhanVien;
    int vitriNhan;
    boolean laGiangVien = GiangVienFragment.laGiangVien;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        //NHẬN 2 arr
        arrGiangVien = getActivity().getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_GIANG_VIEN);
        arrNhanVien = getActivity().getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_NHAN_VIEN);

        //ÁNH XẠ
        Hoten = view.findViewById(R.id.textHoTen);
        Donvi = view.findViewById(R.id.textDViCongTac);
        Heso = view.findViewById(R.id.textHSoLuong);
        Phucap = view.findViewById(R.id.textPhCap);
        So = view.findViewById(R.id.textSo);

        //ĐỂ TEXT VÀO NHƯNG ĐHS K ĐC
        if(laGiangVien) {
            vitriNhan = GiangVienFragment.vitri;
            Hoten.setText(arrGiangVien.get(vitriNhan).getHoTen());
            Donvi.setText(arrGiangVien.get(vitriNhan).getDonViCongTac());
            Heso.setText(Integer.toString(arrGiangVien.get(vitriNhan).getHeSoLuong()));
            Phucap.setText(Integer.toString(arrGiangVien.get(vitriNhan).getPhuCap()));
            So.setText(Integer.toString(arrGiangVien.get(vitriNhan).getSoTietDay()));
        }
        else{
            vitriNhan = NhanVienFragment.vitri;
            Hoten.setText(arrNhanVien.get(vitriNhan).getHoTen());
            Donvi.setText(arrNhanVien.get(vitriNhan).getDonViCongTac());
            Heso.setText(Integer.toString(arrNhanVien.get(vitriNhan).getHeSoLuong()));
            Phucap.setText(Integer.toString(arrNhanVien.get(vitriNhan).getPhuCap()));
            So.setText(Integer.toString(arrNhanVien.get(vitriNhan).getSoNgayCong()));
        }

        builder.setView(view)
                 .setTitle("SỬA")
                 .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                     }
                 })
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         if(laGiangVien) {
                             vitriNhan = GiangVienFragment.vitri;
                             arrGiangVien.set(vitriNhan, new GiangVien(Hoten.getText().toString(),
                                                                       Donvi.getText().toString(),
                                                                       Integer.parseInt(Heso.getText().toString()),
                                                                       Integer.parseInt(Phucap.getText().toString()),
                                                                       Integer.parseInt(So.getText().toString())));
                            }
                         else{
                             vitriNhan = NhanVienFragment.vitri;
                             arrNhanVien.set(vitriNhan, new NhanVien(Hoten.getText().toString(),
                                                                     Donvi.getText().toString(),
                                                                     Integer.parseInt(Heso.getText().toString()),
                                                                     Integer.parseInt(Phucap.getText().toString()),
                                                                     Integer.parseInt(So.getText().toString())));
                            }
                         Toast.makeText(getActivity(), "Đã Sửa Cán Bộ "+ (vitriNhan+1), Toast.LENGTH_SHORT).show();
                     }
                 });
         return builder.create();
    }
}