package com.example.randompeopledemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Name implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("first")
    private String first;
    @SerializedName("last")
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    public Name() {
    }

    protected Name(Parcel in) {
        this.title = in.readString();
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
