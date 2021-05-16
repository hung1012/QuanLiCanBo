package com.example.quanlycanbo.view.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlycanbo.view.Fragment.GiaoVienFragment;
import com.example.quanlycanbo.view.Fragment.NhanVienFragment;

public class CanBoAdapter extends FragmentStatePagerAdapter {//CHUYỂN ĐỔI GIỮA 2 FRAGMENT
                                                            //CHỨC NĂNG KHÁC VỚI 2 ADAPTER CÒN LẠI
    private String listTab[] = {"Giáo Viên", "Nhân Viên"};
    private GiaoVienFragment giaoVienFragment;
    private NhanVienFragment nhanVienFragment;
    public CanBoAdapter(FragmentManager fm) {
        super(fm);
        giaoVienFragment = new GiaoVienFragment();
        nhanVienFragment = new NhanVienFragment();
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return giaoVienFragment;
            case 1:
                return nhanVienFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
