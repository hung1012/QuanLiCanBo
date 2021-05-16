package com.example.quanlycanbo.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.quanlycanbo.R;
import com.example.quanlycanbo.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends BaseAdapter implements Filterable {
    Context context;
    int layout;
    List<NhanVien> arrNhanVien = new ArrayList<>();
    List<NhanVien> arrNhanVienOld = new ArrayList<>();

    public NhanVienAdapter(Context context, int layout, List<NhanVien> arrNhanVien){
        this.context = context;
        this.layout = layout;
        this.arrNhanVien = arrNhanVien;
        this.arrNhanVienOld = arrNhanVien;
    }

    @Override
    public int getCount() {
        return arrNhanVien == null ? 0 : arrNhanVien.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView textHoTen = convertView.findViewById(R.id.textHoTen);
        TextView textDViCongTac = convertView.findViewById(R.id.textDViCongTac);
        TextView textHSoLuong = convertView.findViewById(R.id.textHSoLuong);
        TextView textPhCap = convertView.findViewById(R.id.textPhCap);
        TextView textSoTietDay = convertView.findViewById(R.id.textSo);
        TextView textSTT = convertView.findViewById(R.id.textSTT);
        TextView textLuong = convertView.findViewById(R.id.textLuong);

        textHoTen.setText("Họ Tên: " +arrNhanVien.get(position).getHoTen());
        textDViCongTac.setText("Đơn Vị Công Tác: "+arrNhanVien.get(position).getDonViCongTac());
        textHSoLuong.setText("Hệ Số Lương: "+arrNhanVien.get(position).getHeSoLuong());
        textPhCap.setText("Phụ Cấp: " + arrNhanVien.get(position).getPhuCap());
        textSoTietDay.setText("Số Ngày Công: " +arrNhanVien.get(position).getSoNgayCong());
        textSTT.setText(Integer.toString(position+1));
        textLuong.setText("Lương: " +Double.toString(arrNhanVien.get(position).tinhLuong())+"đ");


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    arrNhanVien = arrNhanVienOld;
                } else{
                    List<NhanVien> list = new ArrayList<>();
                    for(NhanVien nhanVien : arrNhanVienOld){
                        if(nhanVien.getHoTen().toLowerCase().contains(strSearch.toLowerCase())
                                || nhanVien.getDonViCongTac().toLowerCase().contains(strSearch.toLowerCase())
                                ||Double.toString(nhanVien.getHeSoLuong()).contains(strSearch.toLowerCase())){
                            list.add(nhanVien);
                        }
                    }
                    arrNhanVien = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrNhanVien;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrNhanVien = (List<NhanVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
