package com.neilmarietta.flickrsearch.presentation.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.neilmarietta.flickrsearch.R;
import com.neilmarietta.flickrsearch.presentation.model.FlickrPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FlickrPhotoAdapter extends RecyclerView.Adapter<FlickrPhotoAdapter.FlickrPhotoViewHolder> {

    private final LayoutInflater mLayoutInflater;

    private List<FlickrPhoto> mFlickrPhotos = new ArrayList<>();

    public FlickrPhotoAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void clear() {
        int size = mFlickrPhotos.size();
        mFlickrPhotos = new ArrayList<>();
        notifyItemRangeRemoved(0, size);
    }

    public void addFlickrPhotos(List<FlickrPhoto> flickrPhotos) {
        for (FlickrPhoto photo : flickrPhotos)
            addFlickrPhoto(photo);
    }

    public void addFlickrPhoto(FlickrPhoto photo) {
        if (mFlickrPhotos == null)
            mFlickrPhotos = new ArrayList<>();
        mFlickrPhotos.add(photo);
        // RecyclerView uses a DefaultItemAnimator by default.
        notifyItemInserted(mFlickrPhotos.size() - 1);
    }

    public ArrayList<FlickrPhoto> getPhotosCopy() {
        return new ArrayList<>(mFlickrPhotos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (mFlickrPhotos != null) ? mFlickrPhotos.size() : 0;
    }

    @Override
    public FlickrPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mLayoutInflater.inflate(R.layout.cardview_photo, parent, false);
        return new FlickrPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FlickrPhotoViewHolder holder, final int position) {
        final FlickrPhoto photo = mFlickrPhotos.get(position);
        holder.title.setText(photo.getPhoto().getTitle());
        renderPhoto(holder, photo);
    }

    private void renderPhoto(FlickrPhotoViewHolder holder, FlickrPhoto photo) {
        /*
        Context context = holder.photo.getContext();
        Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
        picasso.load(photo.getUrl())
                // Smaller size for less memory use
                .resizeDimen(R.dimen.card_view_photo_width, R.dimen.card_view_photo_height)
                .centerCrop()
                .into(holder.photo);
        */

        Uri uri = Uri.parse(photo.getUrl());
        holder.photo.setImageURI(uri);
    }

    static class FlickrPhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title) TextView title;
        //@Bind(R.id.iv_photo) ImageView photo;
        @Bind(R.id.iv_photo) SimpleDraweeView photo;

        public FlickrPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}