package com.neilmarietta.flickrsearch.data.repository;

import com.neilmarietta.flickrsearch.data.repository.datasource.PhotoSearchResultDataStore;
import com.neilmarietta.flickrsearch.data.repository.datasource.PhotoSearchResultDataStoreFactory;
import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;

import javax.inject.Inject;

import rx.Observable;

public class PhotoSearchResultRepository implements PhotoSearchResultDataStore {

    private final PhotoSearchResultDataStoreFactory mPhotoSearchResultDataStoreFactory;

    @Inject
    public PhotoSearchResultRepository(PhotoSearchResultDataStoreFactory photoSearchResultDataStoreFactory) {
        mPhotoSearchResultDataStoreFactory = photoSearchResultDataStoreFactory;
    }

    @Override
    public Observable<PhotoSearchResult> getPhotoSearchResult(String text, int page) {
        return mPhotoSearchResultDataStoreFactory.create(text).getPhotoSearchResult(text, page);
    }
}
