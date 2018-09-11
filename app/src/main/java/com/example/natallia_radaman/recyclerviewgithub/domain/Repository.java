package com.example.natallia_radaman.recyclerviewgithub.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("stars_count")
    public int stars;

    @SerializedName("forks")
    public int forks;

    @SerializedName("owner")
    public Author author;

    protected Repository(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        stars = in.readInt();
        forks = in.readInt();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(stars);
        parcel.writeInt(forks);
    }
}
