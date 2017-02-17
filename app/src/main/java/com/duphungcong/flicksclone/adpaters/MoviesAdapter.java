package com.duphungcong.flicksclone.adpaters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duphungcong.flicksclone.R;
import com.duphungcong.flicksclone.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by udcun on 2/15/2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {
    private List<Movie> movies;
    // View lockup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackdrop;
    }

    public MoviesAdapter(Context context, List<Movie> objects) {
        super(context, R.layout.movie_in_list, objects);
        movies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate new view for row
        ViewHolder viewHolder;
        if(convertView == null) {
            // If there is now view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_in_list, parent, false);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.ivBackdrop = (ImageView) convertView.findViewById(R.id.ivBackdrop);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bindViewHolder(position, viewHolder);

        return convertView;
    }

    private void bindViewHolder(int position, ViewHolder viewHolder) {
        Movie movie = movies.get(position);

        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            loadImage(viewHolder.ivPoster, movie.getPosterPath(), 300, 500);
        } else {
            loadImage(viewHolder.ivBackdrop, movie.getBackdropPath(), 500, 300);
        }

    }

    private void loadImage(ImageView imageView, String path, int imageWidth, int imageHeight) {
        String imageUrl = "http://image.tmdb.org/t/p/w500" + path;

        Picasso.with(getContext())
                .load(imageUrl)
                .resize(imageWidth, imageHeight)
                .centerCrop()
                .error(R.drawable.deadpool_poster)
                .into(imageView);
    }
}
