package com.example.randompeopledemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("gender")
    private String gender;
    @SerializedName("name")
    private Name name;
    @SerializedName("location")
    private Location location;
    @SerializedName("email")
    private String email;
    @SerializedName("login")
    private Login login;
    @SerializedName("dob")
    private Dob dob;
    @SerializedName("registered")
    private Registered registered;
    @SerializedName("phone")
    private String phone;
    @SerializedName("cell")
    private String cell;
    @SerializedName("id")
    private Id id;
    @SerializedName("picture")
    private Picture picture;
    @SerializedName("nat")
    private String nat;

    protected Result(Parcel in) {
        gender = in.readString();
        email = in.readString();
        phone = in.readString();
        cell = in.readString();
        nat = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeString(nat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
