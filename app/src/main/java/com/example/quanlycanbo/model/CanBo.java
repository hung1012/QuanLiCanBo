package com.example.quanlycanbo.model;

import android.os.Parcel;
import android.os.Parcelable;


public class CanBo implements Parcelable {
    private String hoTen;
    private String donViCongTac;
    private int heSoLuong;
    private int phuCap;
    private CanBo canBo;

    public CanBo() {
        super();
    }

    public CanBo(String hoTen, String donViCongTac, int heSoLuong, int phuCap) {
        this.hoTen = hoTen;
        this.donViCongTac = donViCongTac;
        this.heSoLuong = heSoLuong;
        this.phuCap = phuCap;
    }

    protected CanBo(Parcel in) {
        hoTen = in.readString();
        donViCongTac = in.readString();
        heSoLuong = in.readInt();
        phuCap = in.readInt();
        canBo = in.readParcelable(CanBo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hoTen);
        dest.writeString(donViCongTac);
        dest.writeInt(heSoLuong);
        dest.writeInt(phuCap);
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

    public int getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(int heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(int phuCap) {
        this.phuCap = phuCap;
    }

    public long tinhLuong() {
        return 0;
    }

}