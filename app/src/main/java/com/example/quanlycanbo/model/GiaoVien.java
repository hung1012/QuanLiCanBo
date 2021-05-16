package com.example.quanlycanbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GiaoVien extends CanBo implements Parcelable {
    private int soTietDay;
    private GiaoVien giaoVien;

    public GiaoVien() {
        super();
    }

    public GiaoVien(String hoTen, String donViCongTac, double heSoLuong, double phuCap, int soTietDay) {
        super(hoTen, donViCongTac, heSoLuong, phuCap);
        this.soTietDay = soTietDay;
    }

    public int getSoTietDay() {
        return soTietDay;
    }

    public void setSoTietDay(int soTietDay) {
        this.soTietDay = soTietDay;
    }

    @Override
    public double tinhLuong() {
        return (getHeSoLuong() * 750000+ getPhuCap() + soTietDay * 45000);
    }


    protected GiaoVien(Parcel in) {
        setHoTen(in.readString());
        setDonViCongTac(in.readString());
        setHeSoLuong(in.readDouble());
        setPhuCap(in.readDouble());
        setSoTietDay(in.readInt());
        giaoVien = in.readParcelable(GiaoVien.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getHoTen());
        dest.writeString(getDonViCongTac());
        dest.writeDouble(getHeSoLuong());
        dest.writeDouble(getPhuCap());
        dest.writeInt(soTietDay);
        dest.writeParcelable(giaoVien, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GiaoVien> CREATOR = new Creator<GiaoVien>() {
        @Override
        public GiaoVien createFromParcel(Parcel in) {
            return new GiaoVien(in);
        }

        @Override
        public GiaoVien[] newArray(int size) {
            return new GiaoVien[size];
        }
    };


}