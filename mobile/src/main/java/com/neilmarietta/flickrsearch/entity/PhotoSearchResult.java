package com.neilmarietta.flickrsearch.entity;

import com.google.gson.annotations.SerializedName;

public class PhotoSearchResult {

    @SerializedName("photos")
    private PhotoResultSet mPhotoResultSet;

    @SerializedName("stat")
    private String mStat;

    public PhotoResultSet getPhotoResultSet() {
        return mPhotoResultSet;
    }

    public String getStat() {
        return mStat;
    }
}
