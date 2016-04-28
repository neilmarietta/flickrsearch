package com.neilmarietta.flickrsearch.data.provider;

import android.content.SearchRecentSuggestionsProvider;

public class FlickrSearchRecentSuggestionsProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.neilmarietta.flickrsearch.suggestion.authority";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public FlickrSearchRecentSuggestionsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
