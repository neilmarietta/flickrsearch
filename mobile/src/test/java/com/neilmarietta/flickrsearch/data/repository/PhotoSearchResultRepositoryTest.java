package com.neilmarietta.flickrsearch.data.repository;

import com.neilmarietta.flickrsearch.entity.PhotoSearchResult;
import com.neilmarietta.flickrsearch.internal.di.component.DaggerRepositoriesComponent;

import org.junit.Ignore;
import org.junit.Test;

import rx.Subscriber;

import static org.junit.Assert.assertNotNull;

public class PhotoSearchResultRepositoryTest {

    private static PhotoSearchResultRepository mPhotoSearchResultRepository =
            DaggerRepositoriesComponent.create().photoSearchResultRepository();

    @Ignore
    @Test
    public void getPhotoSearchResultTest() {
        mPhotoSearchResultRepository.getPhotoSearchResult("android", 1)
                .subscribe(new Subscriber<PhotoSearchResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PhotoSearchResult photoSearchResult) {
                        assertNotNull(photoSearchResult);
                    }
                });
    }
}
