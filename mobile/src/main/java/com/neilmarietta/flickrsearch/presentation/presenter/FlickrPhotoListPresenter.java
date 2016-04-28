package com.neilmarietta.flickrsearch.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.neilmarietta.flickrsearch.contract.FlickrPhotoList;
import com.neilmarietta.flickrsearch.interactor.PhotoSearchUseCase;
import com.neilmarietta.flickrsearch.presentation.BasePresenter;
import com.neilmarietta.flickrsearch.presentation.model.FlickrPhoto;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class FlickrPhotoListPresenter extends BasePresenter<FlickrPhotoList.View>
        implements FlickrPhotoList.OnUserActionListener {

    private PhotoSearchUseCase mPhotoSearchUseCase;

    private static final String KEY_CURRENT_TEXT = "current.text";
    private static final String KEY_CURRENT_PAGE = "current.page";
    private String mCurrentText;
    private int mCurrentPage = 1;

    @Inject
    public FlickrPhotoListPresenter(PhotoSearchUseCase photoSearchUseCase) {
        mPhotoSearchUseCase = photoSearchUseCase;
    }

    @Override
    public void attachView(@NonNull FlickrPhotoList.View view, Bundle savedInstanceState) {
        super.attachView(view, savedInstanceState);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
        else
            initialize();
    }

    @Override
    public void detachView() {
        super.detachView();
        mPhotoSearchUseCase.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(KEY_CURRENT_TEXT, mCurrentText);
        bundle.putInt(KEY_CURRENT_PAGE, mCurrentPage);
        super.onSaveInstanceState(bundle);
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentText = savedInstanceState.getString(KEY_CURRENT_TEXT);
        mCurrentPage = savedInstanceState.getInt(KEY_CURRENT_PAGE);
    }

    public void initialize() {
        onSearchNewPhotos("amsterdam");
    }

    @Override
    public void onSearchNewPhotos(String text) {
        mCurrentPage = 1;
        mCurrentText = text;
        getMvpView().clearPhotos();
        getMvpView().showLoading();
        searchPhotos();
    }

    @Override
    public void onLoadNextPhotosPage() {
        getMvpView().showLoading();
        searchPhotos();
    }

    @Override
    public void onRetryButtonClicked() {
        searchPhotos();
    }

    private void searchPhotos() {
        mPhotoSearchUseCase.searchFlickrPhoto(mCurrentText, mCurrentPage, new FlickrPhotoListSubscriber());
    }

    private final class FlickrPhotoListSubscriber extends Subscriber<List<FlickrPhoto>> {

        @Override
        public void onCompleted() {
            FlickrPhotoListPresenter.this.getMvpView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            FlickrPhotoListPresenter.this.getMvpView().hideLoading();
            FlickrPhotoListPresenter.this.getMvpView().showError(e.getMessage());
        }

        @Override
        public void onNext(List<FlickrPhoto> photos) {
            mCurrentPage++;
            FlickrPhotoListPresenter.this.getMvpView().addPhotos(photos);
        }
    }
}
