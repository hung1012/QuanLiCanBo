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
import com.example.quanlycanbo.model.GiangVien;
import com.example.quanlycanbo.view.Adapter.GiangVienAdapter;
import com.example.quanlycanbo.view.Activity.ActivityNhap;
import com.example.quanlycanbo.view.Dialog.DialogThem;
import com.example.quanlycanbo.view.Dialog.DialogSua;

import java.util.ArrayList;

public class GiangVienFragment extends Fragment {
    private ArrayList<GiangVien> arrGiangVien, arrGiangVien1 = new ArrayList<>();
    private ListView lvGiangVien;
    private SearchView searchGV;
    private Button btnThem;
    private Switch switchchuyen;

    public static int vitri;
    public static boolean laGiangVien;
    public static boolean themGiangVien;

    public GiangVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        //ÁNH XẠ
        lvGiangVien = view.findViewById(R.id.listView);
        searchGV = view.findViewById(R.id.searchView);
        btnThem = view.findViewById(R.id.buttonThêm);
        switchchuyen = view.findViewById(R.id.switch1);

        //NHẬN arrGiangViên TỪ CreateLayout
        arrGiangVien = getActivity().getIntent().getParcelableArrayListExtra(ActivityNhap.LIST_GIANG_VIEN);

        //ĐỔ arrGiangVien vào adapter
        GiangVienAdapter adapter = new GiangVienAdapter(getContext(), R.layout.dong_can_bo, arrGiangVien);
        lvGiangVien.setAdapter(adapter);

        //THÊM
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGiangVien = true;
                NhanVienFragment.themNhanVien = false;
                openDialog();
            }
        });

        //XÓA
        lvGiangVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Đã Xóa Giảng Viên Thứ " + (position + 1), Toast.LENGTH_SHORT).show();
                arrGiangVien.remove(position);
                adapter.notifyDataSetChanged();
                lvGiangVien.setAdapter(adapter);
                return true;
            }
        });

        //SỬA
        lvGiangVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
                laGiangVien = true;
                NhanVienFragment.laNhanVien = false;
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
                    arrGiangVien1.clear();
                    arrGiangVien1.addAll(arrGiangVien);
                    ArrayList<GiangVien> arrGiangVien10 = new ArrayList<>();
                    for(GiangVien giangVien : arrGiangVien){
                        if(giangVien.tinhLuong()>10000000){
                            arrGiangVien10.add(giangVien);
                        }
                    }
                    arrGiangVien.clear();
                    arrGiangVien.addAll(arrGiangVien10);
                    adapter.notifyDataSetChanged();
                    lvGiangVien.setAdapter(adapter);
                }
                else{
                    arrGiangVien.clear();
                    arrGiangVien.addAll(arrGiangVien1);
                    adapter.notifyDataSetChanged();
                    lvGiangVien.setAdapter(adapter);
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

