package com.neilmarietta.flickrsearch.presentation;

public interface MvpLoadView extends MvpView {

    void showLoading();

    void hideLoading();

    void showError(String message);
}
