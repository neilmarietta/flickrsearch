package com.neilmarietta.flickrsearch.presentation.presenter;

import com.neilmarietta.flickrsearch.contract.FlickrPhotoList;
import com.neilmarietta.flickrsearch.data.repository.PhotoSearchResultRepository;
import com.neilmarietta.flickrsearch.interactor.PhotoSearchUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.schedulers.Schedulers;

import static org.mockito.MockitoAnnotations.initMocks;

public class FlickrPhotoListPresenterTest {

    private PhotoSearchUseCase mPhotoSearchUseCase;
    private FlickrPhotoListPresenter mFlickrPhotoListPresenter;

    @Mock PhotoSearchResultRepository mPhotoSearchResultRepository;
    @Mock FlickrPhotoList.View mFlickrPhotoListView;

    @Before
    public void setup() {
        initMocks(this);

        mPhotoSearchUseCase = new PhotoSearchUseCase(mPhotoSearchResultRepository);
        mPhotoSearchUseCase.setSubscribeOn(Schedulers.immediate());
        mPhotoSearchUseCase.setObserveOn(Schedulers.immediate());

        mFlickrPhotoListPresenter = new FlickrPhotoListPresenter(mPhotoSearchUseCase);
        mFlickrPhotoListPresenter.attachView(mFlickrPhotoListView, null);
    }

    @After
    public void tearDown() {
        mFlickrPhotoListPresenter.detachView();
        mPhotoSearchUseCase.unsubscribe();
    }

    @Test
    public void searchPhoto() {
        //mFlickrPhotoListPresenter.onSearchNewPhotos("android");
    }

    @Test
    public void nextPhotoPage() {
        //mFlickrPhotoListPresenter.nextPhotoPage();
    }
}
