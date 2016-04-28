package com.neilmarietta.flickrsearch.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.neilmarietta.flickrsearch.entity.Photo;

public class FlickrPhoto implements Parcelable {

    private Photo mPhoto;
    private String mUrl;

    public FlickrPhoto(Photo photo) {
        mPhoto = photo;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public String getUrl() {
        if (mUrl == null)
            mUrl = generateUrl(mPhoto);
        return mUrl;
    }

    private String generateUrl(Photo photo) {
        // http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
        return "http://farm" + photo.getFarm() +
                ".static.flickr.com/" + photo.getServer() +
                "/" + photo.getId() +
                "_" + photo.getSecret() + ".jpg";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(mPhoto, flags);
    }

    public static final Parcelable.Creator<FlickrPhoto> CREATOR = new Parcelable.Creator<FlickrPhoto>() {
        public FlickrPhoto createFromParcel(Parcel in) {
            return new FlickrPhoto(in);
        }

        public FlickrPhoto[] newArray(int size) {
            return new FlickrPhoto[size];
        }
    };

    private FlickrPhoto(Parcel in) {
        mPhoto = in.readParcelable(Photo.class.getClassLoader());
    }
}
