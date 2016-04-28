package com.neilmarietta.flickrsearch.interactor;

import com.neilmarietta.flickrsearch.data.repository.PhotoSearchResultRepository;
import com.neilmarietta.flickrsearch.entity.Photo;
import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;
import com.neilmarietta.flickrsearch.presentation.model.FlickrPhoto;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class PhotoSearchUseCase extends UseCase {

    private final PhotoSearchResultRepository mPhotoSearchResultRepository;

    public PhotoSearchUseCase(PhotoSearchResultRepository photoSearchResultRepository) {
        mPhotoSearchResultRepository = photoSearchResultRepository;
    }

    public void searchFlickrPhoto(String text, int page, Subscriber subscriber) {
        execute(searchFlickrPhoto(text, page), subscriber);
    }

    private Observable<List<FlickrPhoto>> searchFlickrPhoto(String text, int page) {
        return mPhotoSearchResultRepository.getPhotoSearchResult(text, page)
                .map(new Func1<PhotoSearchResult, List<Photo>>() {
                    @Override
                    public List<Photo> call(PhotoSearchResult photoSearchResult) {
                        return photoSearchResult.getPhotoResultSet().getPhotoSet();
                    }
                })
                .flatMap(new Func1<List<Photo>, Observable<Photo>>() {
                    @Override
                    public Observable<Photo> call(List<Photo> photos) {
                        return Observable.from(photos);
                    }
                })
                .map(new Func1<Photo, FlickrPhoto>() {
                    @Override
                    public FlickrPhoto call(Photo photo) {
                        return new FlickrPhoto(photo);
                    }
                })
                .toList();
    }
}
