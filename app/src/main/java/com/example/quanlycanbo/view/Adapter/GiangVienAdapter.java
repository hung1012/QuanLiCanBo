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
import com.example.quanlycanbo.model.GiangVien;

import java.util.ArrayList;
import java.util.List;

public class GiangVienAdapter extends BaseAdapter implements Filterable {
    Context context;
    int layout;
    List<GiangVien> arrGiangVien = new ArrayList<>();
    //Tạo List khác phục vụ việc tìm kiếm
    List<GiangVien> arrGiangVienOld = new ArrayList<>();
    public GiangVienAdapter(Context context, int layout, List<GiangVien> arrGiangVien){
        this.context = context;
        this.layout = layout;
        this.arrGiangVien = arrGiangVien;
        this.arrGiangVienOld = arrGiangVien;
    }

    @Override
    public int getCount() {
        return arrGiangVien == null ? 0 : arrGiangVien.size();
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

        textHoTen.setText("Họ Tên: " +arrGiangVien.get(position).getHoTen());
        textDViCongTac.setText("Đơn Vị Công Tác: "+arrGiangVien.get(position).getDonViCongTac());
        textHSoLuong.setText("Hệ Số Lương: "+ arrGiangVien.get(position).getHeSoLuong());
        textPhCap.setText("Phụ Cấp: "+arrGiangVien.get(position).getPhuCap());
        textSoTietDay.setText("Số Tiết Dạy: "+arrGiangVien.get(position).getSoTietDay());
        textSTT.setText(Integer.toString(position+1));
        textLuong.setText("Lương: " +Long.toString(arrGiangVien.get(position).tinhLuong())+"đ");

        return convertView;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    arrGiangVien = arrGiangVienOld;
                } else{
                    List<GiangVien> list = new ArrayList<>();
                    for(GiangVien giangVien : arrGiangVienOld){
                        if(giangVien.getHoTen().toLowerCase().contains(strSearch.toLowerCase())
                         || giangVien.getDonViCongTac().toLowerCase().contains(strSearch.toLowerCase())
                         ||Integer.toString(giangVien.getHeSoLuong()).contains(strSearch.toLowerCase())){
                            list.add(giangVien);
                        }
                    }
                    arrGiangVien = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrGiangVien;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrGiangVien = (List<GiangVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
