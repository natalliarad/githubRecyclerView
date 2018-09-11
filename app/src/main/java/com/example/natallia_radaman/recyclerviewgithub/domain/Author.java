package com.example.natallia_radaman.recyclerviewgithub.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Author implements Parcelable {
    @SerializedName("id")
    public int id;

    @SerializedName("login")
    public String login;

    @SerializedName("url_avatar")
    public String avatar;

    @Override
    public String toString() {
        return "[ " + id + ", " + login + "," + avatar + "]";
    }

    protected Author(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatar = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(login);
        parcel.writeString(avatar);
    }
}
