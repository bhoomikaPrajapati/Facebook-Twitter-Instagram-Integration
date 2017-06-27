package com.demoapp.facebook_post.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vikas on 26/6/17.
 */

public class FacebookData implements Parcelable {
    @SerializedName("data")
    private ArrayList<FacebookPost> facebookPostArrayList;

    protected FacebookData(Parcel in) {
    }

    public static final Creator<FacebookData> CREATOR = new Creator<FacebookData>() {
        @Override
        public FacebookData createFromParcel(Parcel in) {
            return new FacebookData(in);
        }

        @Override
        public FacebookData[] newArray(int size) {
            return new FacebookData[size];
        }
    };

    public ArrayList<FacebookPost> getFacebookPostArrayList() {
        return facebookPostArrayList;
    }

    public void setFacebookPostArrayList(ArrayList<FacebookPost> facebookPostArrayList) {
        this.facebookPostArrayList = facebookPostArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
