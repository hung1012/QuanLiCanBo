package com.example.quanlycanbo.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.GiaoVien;
import com.example.quanlycanbo.view.Adapter.GiaoVienAdapter;
import com.example.quanlycanbo.view.Activity.Activity2;
import com.example.quanlycanbo.view.Dialog.DialogThem;
import com.example.quanlycanbo.view.Dialog.DialogSua;

import java.util.ArrayList;

public class GiaoVienFragment extends Fragment {
    private ArrayList<GiaoVien> arrGiaoVien, arrGiaoVien1 = new ArrayList<>();
    private ListView lvGiaoVien;
    private SearchView searchGV;
    private Button btnThem;
    private Switch switchchuyen;

    public static int vitri;
    public static boolean suaGiaoVien;
    public static boolean themGiaoVien;

    public GiaoVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        //ÁNH XẠ
        lvGiaoVien = view.findViewById(R.id.listView);
        searchGV = view.findViewById(R.id.searchView);
        btnThem = view.findViewById(R.id.buttonThêm);
        switchchuyen = view.findViewById(R.id.switch1);

        //NHẬN arrGiaoViên TỪ CreateLayout
        arrGiaoVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_GIAO_VIEN);
        //ĐỔ arrGiaoVien vào adapter
        GiaoVienAdapter adapter = new GiaoVienAdapter(getContext(), R.layout.dong_can_bo, arrGiaoVien);
        lvGiaoVien.setAdapter(adapter);

        //THÊM
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGiaoVien = true;
                NhanVienFragment.themNhanVien = false;
                openDialog();
            }
        });

        //XÓA
        lvGiaoVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Đã Xóa Giáo Viên Thứ " + (position + 1), Toast.LENGTH_SHORT).show();
                arrGiaoVien.remove(position);
                adapter.notifyDataSetChanged();
                lvGiaoVien.setAdapter(adapter);
                return true;
            }
        });

        //SỬA
        lvGiaoVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
                suaGiaoVien = true;
                NhanVienFragment.suaNhanVien = false;
                openDialogSuaCanBo();
            }
        });

        //TÌM KIẾM
        searchGV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        //GIẢNG VIÊN LƯƠNG TRÊN 10TR
        switchchuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrGiaoVien1.clear();
                    arrGiaoVien1.addAll(arrGiaoVien);
                    ArrayList<GiaoVien> arrGiaoVien10 = new ArrayList<>();
                    for(GiaoVien giaoVien : arrGiaoVien){
                        if(giaoVien.tinhLuong()>10000000){
                            arrGiaoVien10.add(giaoVien);
                        }
                    }
                    arrGiaoVien.clear();
                    arrGiaoVien.addAll(arrGiaoVien10);
                    adapter.notifyDataSetChanged();
                    lvGiaoVien.setAdapter(adapter);
                }
                else{
                    arrGiaoVien.clear();
                    arrGiaoVien.addAll(arrGiaoVien1);
                    adapter.notifyDataSetChanged();
                    lvGiaoVien.setAdapter(adapter);
                }
            }
        });

        return view;
    }
    private void openDialog() {
        DialogThem dialog = new DialogThem();
        dialog.show(getFragmentManager(), "Dialog");
    }
    private void openDialogSuaCanBo() {
        DialogSua dialog = new DialogSua();
        dialog.show(getFragmentManager(), "Dialogthem");
    }
}

