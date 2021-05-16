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
import com.example.quanlycanbo.model.GiaoVien;

import java.util.ArrayList;
import java.util.List;

public class GiaoVienAdapter extends BaseAdapter implements Filterable {
    Context context;
    int layout;
    List<GiaoVien> arrGiaoVien = new ArrayList<>();
    //Tạo List khác phục vụ việc tìm kiếm
    List<GiaoVien> arrGiaoVienOld = new ArrayList<>();
    public GiaoVienAdapter(Context context, int layout, List<GiaoVien> arrGiaoVien){
        this.context = context;
        this.layout = layout;
        this.arrGiaoVien = arrGiaoVien;
        this.arrGiaoVienOld = arrGiaoVien;
    }

    @Override
    public int getCount() {
        return arrGiaoVien == null ? 0 : arrGiaoVien.size();
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

        textHoTen.setText("Họ Tên: " + arrGiaoVien.get(position).getHoTen());
        textDViCongTac.setText("Đơn Vị Công Tác: "+ arrGiaoVien.get(position).getDonViCongTac());
        textHSoLuong.setText("Hệ Số Lương: "+ arrGiaoVien.get(position).getHeSoLuong());
        textPhCap.setText("Phụ Cấp: "+ arrGiaoVien.get(position).getPhuCap());
        textSoTietDay.setText("Số Tiết Dạy: "+ arrGiaoVien.get(position).getSoTietDay());
        textSTT.setText(Integer.toString(position+1));
        textLuong.setText("Lương: " +Double.toString(arrGiaoVien.get(position).tinhLuong())+"đ");

        return convertView;
    }

    //TÌM KIẾM
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    arrGiaoVien = arrGiaoVienOld;
                } else{
                    List<GiaoVien> list = new ArrayList<>();
                    for(GiaoVien giaoVien : arrGiaoVienOld){
                        if(giaoVien.getHoTen().toLowerCase().contains(strSearch.toLowerCase())
                         || giaoVien.getDonViCongTac().toLowerCase().contains(strSearch.toLowerCase())
                         ||Double.toString(giaoVien.getHeSoLuong()).contains(strSearch.toLowerCase())){
                            list.add(giaoVien);
                        }
                    }
                    arrGiaoVien = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrGiaoVien;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrGiaoVien = (List<GiaoVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
