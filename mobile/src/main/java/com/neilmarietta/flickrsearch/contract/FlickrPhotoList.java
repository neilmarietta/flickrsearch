package com.neilmarietta.flickrsearch.contract;

import com.neilmarietta.flickrsearch.presentation.MvpLoadView;
import com.neilmarietta.flickrsearch.presentation.model.FlickrPhoto;

import java.util.List;

public class FlickrPhotoList {

    public interface View extends MvpLoadView {

        void clearPhotos();

        void searchPhotos(String text);

        void addPhotos(List<FlickrPhoto> photos);
    }

    public interface OnUserActionListener {

        void onSearchNewPhotos(String text);

        void onLoadNextPhotosPage();

        void onRetryButtonClicked();
    }
}
