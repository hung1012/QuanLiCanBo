package com.example.quanlycanbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GiangVien extends CanBo implements Parcelable {
    private int soTietDay;
    private GiangVien giangVien;

    public GiangVien() {
        super();
    }

    public GiangVien(String hoTen, String donViCongTac, int heSoLuong, int phuCap, int soTietDay) {
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
    public long tinhLuong() {
        return (long) (getHeSoLuong() * 750000+ getPhuCap() + soTietDay * 45000);
    }


    protected GiangVien(Parcel in) {
        setHoTen(in.readString());
        setDonViCongTac(in.readString());
        setHeSoLuong(in.readInt());
        setPhuCap(in.readInt());
        giangVien = in.readParcelable(GiangVien.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getHoTen());
        dest.writeString(getDonViCongTac());
        dest.writeInt(getHeSoLuong());
        dest.writeInt(getPhuCap());
        dest.writeInt(soTietDay);
        dest.writeParcelable(giangVien, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GiangVien> CREATOR = new Creator<GiangVien>() {
        @Override
        public GiangVien createFromParcel(Parcel in) {
            return new GiangVien(in);
        }

        @Override
        public GiangVien[] newArray(int size) {
            return new GiangVien[size];
        }
    };


}