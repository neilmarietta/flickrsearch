package com.neilmarietta.flickrsearch.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("id")
    private String mId;

    @SerializedName("owner")
    private String mOwner;

    @SerializedName("secret")
    private String mSecret;

    @SerializedName("server")
    private String mServer;

    @SerializedName("farm")
    private int mFarm;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("ispublic")
    private byte mIsPublic;

    @SerializedName("isfriend")
    private byte mIsFriend;

    @SerializedName("isfamily")
    private byte mIsFamily;

    public String getId() {
        return mId;
    }

    public String getOwner() {
        return mOwner;
    }

    public String getSecret() {
        return mSecret;
    }

    public String getServer() {
        return mServer;
    }

    public int getFarm() {
        return mFarm;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isPublic() {
        return mIsPublic == 1;
    }

    public boolean isFriend() {
        return mIsFriend == 1;
    }

    public boolean isFamily() {
        return mIsFamily == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mId);
        out.writeString(mOwner);
        out.writeString(mSecret);
        out.writeString(mServer);
        out.writeInt(mFarm);
        out.writeString(mTitle);
        out.writeByte(mIsPublic);
        out.writeByte(mIsFriend);
        out.writeByte(mIsFamily);
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    private Photo(Parcel in) {
        mId = in.readString();
        mOwner = in.readString();
        mSecret = in.readString();
        mServer = in.readString();
        mFarm = in.readInt();
        mTitle = in.readString();
        mIsPublic = in.readByte();
        mIsFriend = in.readByte();
        mIsFamily = in.readByte();
    }
}
