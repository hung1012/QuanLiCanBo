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
import com.example.quanlycanbo.model.NhanVien;
import com.example.quanlycanbo.view.Activity.Activity2;
import com.example.quanlycanbo.view.Dialog.DialogThem;
import com.example.quanlycanbo.view.Dialog.DialogSua;
import com.example.quanlycanbo.view.Adapter.NhanVienAdapter;

import java.util.ArrayList;

public class NhanVienFragment extends Fragment {
    private ArrayList<NhanVien> arrNhanVien, arrNhanVien1 = new ArrayList<>();
    private ListView lvNhanVien;
    private SearchView searchNV;
    private Button btnThem;
    private Switch switchchuyen;

    public static int vitri;
    public static boolean themNhanVien;
    public static boolean laNhanVien;

    public NhanVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        //ÁNH XẠ
        lvNhanVien= view.findViewById(R.id.listView);
        searchNV = view.findViewById(R.id.searchView);
        btnThem=view.findViewById(R.id.buttonThêm);
        switchchuyen=view.findViewById(R.id.switch1);

        searchNV.setQueryHint("Tìm Kiếm Nhân Viên");

        //NHẬN arrNhanVien TỪ CreateLayout
        arrNhanVien = getActivity().getIntent().getParcelableArrayListExtra(Activity2.LIST_NHAN_VIEN);

        //ĐỔ arrNhanVien VÀO adapter
        NhanVienAdapter adapter = new NhanVienAdapter(getContext(), R.layout.dong_can_bo, arrNhanVien);
        lvNhanVien.setAdapter(adapter);

        //THÊM
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themNhanVien = true;
                GiaoVienFragment.themGiaoVien = false;
                openDialog();
            }
        });

        //XÓA
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Đã Xóa Nhân Viên Thứ "+(position+1), Toast.LENGTH_SHORT).show();
                arrNhanVien.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        //SỬA
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
                laNhanVien = true;
                GiaoVienFragment.laGiaoVien = false;
                openDialogSuaCanBo();
                adapter.notifyDataSetChanged();
            }
        });

        //TÌM KIẾM
        searchNV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        //NHÂN VIÊN LƯƠNG TRÊN 10TR
        switchchuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrNhanVien1.clear();
                    arrNhanVien1.addAll(arrNhanVien);
                    ArrayList<NhanVien> arrNhanVien10 = new ArrayList<>();
                    for(NhanVien nhanVien : arrNhanVien){
                        if(nhanVien.tinhLuong()>10000000){
                            arrNhanVien10.add(nhanVien);
                        }
                    }
                    arrNhanVien.clear();
                    arrNhanVien.addAll(arrNhanVien10);
                    adapter.notifyDataSetChanged();
                    lvNhanVien.setAdapter(adapter);
                }
                else{
                    arrNhanVien.clear();
                    arrNhanVien.addAll(arrNhanVien1);
                    adapter.notifyDataSetChanged();
                    lvNhanVien.setAdapter(adapter);
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

