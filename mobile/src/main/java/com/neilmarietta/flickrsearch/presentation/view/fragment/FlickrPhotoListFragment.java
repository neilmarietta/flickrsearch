package com.neilmarietta.flickrsearch.presentation.view.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.neilmarietta.flickrsearch.FlickrSearchApplication;
import com.neilmarietta.flickrsearch.R;
import com.neilmarietta.flickrsearch.contract.FlickrPhotoList;
import com.neilmarietta.flickrsearch.data.provider.FlickrSearchRecentSuggestionsProvider;
import com.neilmarietta.flickrsearch.internal.di.component.DaggerPhotoSearchComponent;
import com.neilmarietta.flickrsearch.presentation.model.FlickrPhoto;
import com.neilmarietta.flickrsearch.presentation.presenter.FlickrPhotoListPresenter;
import com.neilmarietta.flickrsearch.presentation.view.adapter.FlickrPhotoAdapter;
import com.neilmarietta.flickrsearch.presentation.view.controller.ZoomImageViewController;
import com.neilmarietta.flickrsearch.presentation.view.listener.EndlessRecyclerViewOnScrollListener;
import com.neilmarietta.flickrsearch.presentation.view.manager.ExtraPageGridLayoutManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FlickrPhotoListFragment extends Fragment implements FlickrPhotoList.View {

    private static final String KEY_CURRENT_PHOTOS = "current.photos";

    @Inject FlickrPhotoListPresenter mListPresenter;

    @Bind(R.id.rv_photos) RecyclerView mFlickrPhotoRecycledView;
    @Bind(R.id.rl_progress) RelativeLayout mProgressRelativeLayout;

    private FlickrPhotoAdapter mFlickrPhotoAdapter;

    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private SearchRecentSuggestions mSearchRecentSuggestions;

    private Snackbar mCurrentSnackBar;

    private ZoomImageViewController mZoomImageViewController;

    public FlickrPhotoListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mFlickrPhotoAdapter = new FlickrPhotoAdapter(getContext());
        mSearchRecentSuggestions = new SearchRecentSuggestions(getContext(),
                FlickrSearchRecentSuggestionsProvider.AUTHORITY, FlickrSearchRecentSuggestionsProvider.MODE);

        DaggerPhotoSearchComponent.builder()
                .apiConnectionComponent(FlickrSearchApplication.from(getContext()).getApiConnectionComponent())
                .build().inject(this);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_CURRENT_PHOTOS, mFlickrPhotoAdapter.getPhotosCopy());

        // Save Presenter state (will be restored onViewCreated -> attachView)
        mListPresenter.onSaveInstanceState(outState);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        mFlickrPhotoAdapter.addFlickrPhotos(savedInstanceState.<FlickrPhoto>getParcelableArrayList(KEY_CURRENT_PHOTOS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        setupZoomImageViewController(view, (ImageView) view.findViewById(R.id.iv_expanded));
        return view;
    }

    private void setupRecyclerView() {
        final ExtraPageGridLayoutManager gridLayoutManager = new ExtraPageGridLayoutManager(getContext(), 3);
        mFlickrPhotoRecycledView.setLayoutManager(gridLayoutManager);
        mFlickrPhotoRecycledView.setAdapter(mFlickrPhotoAdapter);
        mFlickrPhotoRecycledView.setHasFixedSize(true);
        mFlickrPhotoRecycledView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadNextPage() {
                mListPresenter.onLoadNextPhotosPage();
            }
        });
    }

    private void setupZoomImageViewController(@NonNull View container, @NonNull ImageView expandedView) {
        mZoomImageViewController =
                new ZoomImageViewController(container, expandedView,
                        getResources().getInteger(android.R.integer.config_shortAnimTime));
        mFlickrPhotoAdapter.setOnItemClickListener(new FlickrPhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, FlickrPhoto photo) {
                mZoomImageViewController.zoomImageFromThumb(view, photo.getUrl());
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListPresenter.attachView(this, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListPresenter.detachView();
    }

    @Override
    public void searchPhotos(String text) {
        mListPresenter.onSearchNewPhotos(text);
        MenuItemCompat.collapseActionView(mSearchItem);
        mSearchRecentSuggestions.saveRecentQuery(text, null);
    }

    @Override
    public void clearPhotos() {
        mFlickrPhotoAdapter.clear();
    }

    @Override
    public void addPhotos(@NonNull List<FlickrPhoto> photos) {
        mFlickrPhotoAdapter.addFlickrPhotos(photos);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_photo_list_menu, menu);
        setupSearchView(menu.findItem(R.id.menu_search));
    }

    private void setupSearchView(final MenuItem searchItem) {
        mSearchItem = searchItem;
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null)
            mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            mSearchView.setIconifiedByDefault(false);
            // Handle query submit from SearchView input method
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchPhotos(query);
                    return true;
                }
            });
        }
    }

    @Override
    public void showLoading() {
        if (mCurrentSnackBar != null) mCurrentSnackBar.dismiss();
        mProgressRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mCurrentSnackBar != null) mCurrentSnackBar.dismiss();
        mProgressRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        mCurrentSnackBar = Snackbar.make(mFlickrPhotoRecycledView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListPresenter.onRetryButtonClicked();
                    }
                });
        mCurrentSnackBar.show();
    }
}
