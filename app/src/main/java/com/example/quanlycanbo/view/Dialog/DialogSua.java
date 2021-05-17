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
import com.example.quanlycanbo.view.Fragment.NhanVienFragment;

import java.util.ArrayList;

public class DialogSua extends AppCompatDialogFragment {
    private EditText Hoten;
    private EditText Donvi;
    private EditText Heso;
    private EditText Phucap;
    private EditText So;
    private ArrayList<GiaoVien> arrGiaoVien;
    private ArrayList<NhanVien> arrNhanVien;
    int vitriNhan;
    boolean laGiangVien = GiaoVienFragment.laGiaoVien;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        //NHẬN 2 arr
        arrGiaoVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_GIAO_VIEN);
        arrNhanVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_NHAN_VIEN);

        //ÁNH XẠ
        Hoten = view.findViewById(R.id.textHoTen);
        Donvi = view.findViewById(R.id.textDViCongTac);
        Heso = view.findViewById(R.id.textHSoLuong);
        Phucap = view.findViewById(R.id.textPhCap);
        So = view.findViewById(R.id.textSo);

        //ĐỂ TEXT VÀO EDITTEXT
        if(laGiangVien) {
            vitriNhan = GiaoVienFragment.vitri;
            Hoten.setText(arrGiaoVien.get(vitriNhan).getHoTen());
            Donvi.setText(arrGiaoVien.get(vitriNhan).getDonViCongTac());
            Heso.setText(Double.toString(arrGiaoVien.get(vitriNhan).getHeSoLuong()));
            Phucap.setText(Double.toString(arrGiaoVien.get(vitriNhan).getPhuCap()));
            So.setText(Integer.toString(arrGiaoVien.get(vitriNhan).getSoTietDay()));
        }
        else{
            vitriNhan = NhanVienFragment.vitri;
            Hoten.setText(arrNhanVien.get(vitriNhan).getHoTen());
            Donvi.setText(arrNhanVien.get(vitriNhan).getDonViCongTac());
            Heso.setText(Double.toString(arrNhanVien.get(vitriNhan).getHeSoLuong()));
            Phucap.setText(Double.toString(arrNhanVien.get(vitriNhan).getPhuCap()));
            So.setText(Integer.toString(arrNhanVien.get(vitriNhan).getSoNgayCong()));
            So.setHint("Số Ngày Công");
        }

        builder.setView(view)
                 .setTitle("SỬA")/*TẠO 1 NÚT LÙI*/
                 .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                     }
                 })/*TẠO 1 NÚT TIẾP TỤC*/
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         if(laGiangVien) {
                             vitriNhan = GiaoVienFragment.vitri;
                             arrGiaoVien.set(vitriNhan, new GiaoVien(Hoten.getText().toString(),
                                                                       Donvi.getText().toString(),
                                                                       Double.parseDouble(Heso.getText().toString()),
                                                                       Double.parseDouble(Phucap.getText().toString()),
                                                                       Integer.parseInt(So.getText().toString())));
                            }
                         else{
                             vitriNhan = NhanVienFragment.vitri;
                             arrNhanVien.set(vitriNhan, new NhanVien(Hoten.getText().toString(),
                                                                     Donvi.getText().toString(),
                                                                     Double.parseDouble(Heso.getText().toString()),
                                                                     Double.parseDouble(Phucap.getText().toString()),
                                                                     Integer.parseInt(So.getText().toString())));
                            }
                         Toast.makeText(getActivity(), "Đã Sửa Cán Bộ "+ (vitriNhan+1), Toast.LENGTH_SHORT).show();
                     }
                 });
         return builder.create();
    }
}
