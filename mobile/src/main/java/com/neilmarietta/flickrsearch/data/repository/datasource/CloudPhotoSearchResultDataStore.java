package com.neilmarietta.flickrsearch.data.repository.datasource;

import com.neilmarietta.flickrsearch.data.repository.net.FlickrPhotoSearchRestApi;
import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;

import retrofit2.Retrofit;
import rx.Observable;

public class CloudPhotoSearchResultDataStore implements PhotoSearchResultDataStore {

    private static final String METHOD = "flickr.photos.search";
    // https://www.flickr.com/services/api/misc.api_keys.html
    private static final String API_KEY = "GenerateYourOwn";
    private static final String FORMAT = "json";
    private static final byte NON_JSON_CALLBACK = 1; // true

    private final FlickrPhotoSearchRestApi mRestService;

    public CloudPhotoSearchResultDataStore(Retrofit adapter) {
        mRestService = adapter.create(FlickrPhotoSearchRestApi.class);
    }

    @Override
    public Observable<PhotoSearchResult> getPhotoSearchResult(String text, int page) {
        return mRestService.getPhotoSearchResult(METHOD, API_KEY, FORMAT, NON_JSON_CALLBACK, text, page);
    }
}
