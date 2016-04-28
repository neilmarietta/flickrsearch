package com.neilmarietta.flickrsearch.interactor;

import com.neilmarietta.flickrsearch.data.repository.PhotoSearchResultRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.schedulers.Schedulers;

import static org.mockito.MockitoAnnotations.initMocks;

public class PhotoSearchUseCaseTest {

    private PhotoSearchUseCase mPhotoSearchUseCase;

    @Mock PhotoSearchResultRepository mPhotoSearchResultRepository;

    @Before
    public void setup() {
        initMocks(this);

        mPhotoSearchUseCase = new PhotoSearchUseCase(mPhotoSearchResultRepository);
        mPhotoSearchUseCase.setSubscribeOn(Schedulers.immediate());
        mPhotoSearchUseCase.setObserveOn(Schedulers.immediate());
    }

    @After
    public void tearDown() {
        mPhotoSearchUseCase.unsubscribe();
    }

    @Test
    public void getMessageTest() {
        /*
        // Subscribe Observable
        TestSubscriber<List<FlickrPhoto>> testSubscriber = new TestSubscriber<>();
        mPhotoSearchUseCase.searchFlickrPhoto("test", 1, testSubscriber)
        testSubscriber.assertNoErrors();

        List<FlickrPhoto> expected = ...
        List<FlickrPhoto> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(expected, actual);
        */
    }
}
