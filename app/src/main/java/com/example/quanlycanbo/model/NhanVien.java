package com.example.quanlycanbo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.quanlycanbo.model.CanBo;

public class NhanVien extends CanBo implements Parcelable {
    private int soNgayCong;
    NhanVien nhanVien;

    public NhanVien() {
        super();
    }

    public NhanVien(String hoTen, String donViCongTac, double heSoLuong, double phuCap, int soNgayCong) {
        super(hoTen, donViCongTac, heSoLuong, phuCap);
        this.soNgayCong = soNgayCong;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    @Override
    public double tinhLuong() { return (getHeSoLuong() * 750000 + getPhuCap() + soNgayCong * 200000); }


    protected NhanVien(Parcel in) {
        setHoTen(in.readString());
        setDonViCongTac(in.readString());
        setHeSoLuong(in.readDouble());
        setPhuCap(in.readDouble());
        soNgayCong = in.readInt();
        nhanVien = in.readParcelable(NhanVien.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getHoTen());
        dest.writeString(getDonViCongTac());
        dest.writeDouble(getHeSoLuong());
        dest.writeDouble(getPhuCap());
        dest.writeInt(soNgayCong);
        dest.writeParcelable(nhanVien, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NhanVien> CREATOR = new Creator<NhanVien>() {
        @Override
        public NhanVien createFromParcel(Parcel in) {
            return new NhanVien(in);
        }

        @Override
        public NhanVien[] newArray(int size) { return new NhanVien[size]; }
    };

}
