package com.example.quanlycanbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CanBo implements Parcelable {
    private String hoTen;
    private String donViCongTac;
    private double heSoLuong;
    private double phuCap;
    public CanBo canBo;

    public CanBo() {
    }

    public CanBo(String hoTen, String donViCongTac, double heSoLuong, double phuCap) {
        this.hoTen = hoTen;
        this.donViCongTac = donViCongTac;
        this.heSoLuong = heSoLuong;
        this.phuCap = phuCap;
    }

    protected CanBo(Parcel in) {
        hoTen = in.readString();
        donViCongTac = in.readString();
        heSoLuong = in.readDouble();
        phuCap = in.readDouble();
        canBo = in.readParcelable(CanBo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hoTen);
        dest.writeString(donViCongTac);
        dest.writeDouble(heSoLuong);
        dest.writeDouble(phuCap);
        dest.writeParcelable(canBo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CanBo> CREATOR = new Creator<CanBo>() {
        @Override
        public CanBo createFromParcel(Parcel in) {
            return new CanBo(in);
        }

        @Override
        public CanBo[] newArray(int size) {
            return new CanBo[size];
        }
    };

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDonViCongTac() {
        return donViCongTac;
    }

    public void setDonViCongTac(String donViCongTac) {
        this.donViCongTac = donViCongTac;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double tinhLuong() {
        return 0;
    }

}