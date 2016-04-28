package com.neilmarietta.flickrsearch.data.repository.datasource;

import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;

import rx.Observable;

public interface PhotoSearchResultDataStore {

    Observable<PhotoSearchResult> getPhotoSearchResult(String text, int page);
}
