package com.neilmarietta.flickrsearch.data.repository.datasource;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class PhotoSearchResultDataStoreFactory {

    private final Retrofit mAdapter;

    @Inject
    public PhotoSearchResultDataStoreFactory(Retrofit adapter) {
        mAdapter = adapter;
    }

    public PhotoSearchResultDataStore create(String text) {
        // You can return/create different PhotoSearchResultDataStore here
        // For example : createCachedPhotoSearchResultDataStore
        return createCloudPhotoSearchResultDataStore();
    }

    public PhotoSearchResultDataStore createCloudPhotoSearchResultDataStore() {
        return new CloudPhotoSearchResultDataStore(mAdapter);
    }
}
