package com.neilmarietta.flickrsearch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoResultSet {

    @SerializedName("page")
    private int mPage;

    @SerializedName("pages")
    private String mPages;

    @SerializedName("perpage")
    private int mPerPage;

    @SerializedName("total")
    private String mTotal;

    @SerializedName("photo")
    private List<Photo> mPhotoSet;

    public int getPage() {
        return mPage;
    }

    public String getPages() {
        return mPages;
    }

    public int getPerPage() {
        return mPerPage;
    }

    public String getTotal() {
        return mTotal;
    }

    public List<Photo> getPhotoSet() {
        return mPhotoSet;
    }
}
