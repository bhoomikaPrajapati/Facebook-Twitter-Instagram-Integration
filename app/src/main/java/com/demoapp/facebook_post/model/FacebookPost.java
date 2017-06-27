package com.demoapp.facebook_post.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikas on 26/6/17.
 */

public class FacebookPost implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("message")
    private String message;
    @SerializedName("full_picture")
    private String full_picture;
    @SerializedName("created_time")
    private String created_time;

    protected FacebookPost(Parcel in) {
        id = in.readString();
        message = in.readString();
        full_picture = in.readString();
        created_time = in.readString();
    }

    public static final Creator<FacebookPost> CREATOR = new Creator<FacebookPost>() {
        @Override
        public FacebookPost createFromParcel(Parcel in) {
            return new FacebookPost(in);
        }

        @Override
        public FacebookPost[] newArray(int size) {
            return new FacebookPost[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(message);
        dest.writeString(full_picture);
        dest.writeString(created_time);
    }
}
