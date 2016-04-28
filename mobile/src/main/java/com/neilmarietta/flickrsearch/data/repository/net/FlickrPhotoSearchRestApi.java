package com.neilmarietta.flickrsearch.data.repository.net;

import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrPhotoSearchRestApi {

    // TODO: Find a way to add the 4 first query parameters by default

    @GET("/services/rest/")
    Observable<PhotoSearchResult> getPhotoSearchResult(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int nojsoncallback,
            @Query("text") String text,
            @Query("page") int page);
}
