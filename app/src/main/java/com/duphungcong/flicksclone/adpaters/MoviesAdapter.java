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
    private static class ViewHolderForNormalMovie {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackdrop;

        public ViewHolderForNormalMovie(View view) {
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvOverview = (TextView) view.findViewById(R.id.tvOverview);
            this.ivPoster = (ImageView) view.findViewById(R.id.ivPoster);
            this.ivBackdrop = (ImageView) view.findViewById(R.id.ivBackdrop);
        }
    }

    private static class ViewHolderForPopularMovie {
        ImageView ivBackdrop;

        public ViewHolderForPopularMovie(View view) {
            this.ivBackdrop = (ImageView) view.findViewById(R.id.ivBackdrop);
        }
    }

    public MoviesAdapter(Context context, List<Movie> objects) {
        super(context, R.layout.normal_movie_in_list, objects);
        movies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        int recycledViewType = getRecycledViewType(convertView);
        int viewTypeCount = getViewTypeCount(); // For reference. May be used in future, when parent view become complex.

        // viewTypeCount = 2. Make sure we have two cases of itemViewType
        switch (itemViewType) {
            case 0:
                ViewHolderForNormalMovie viewHolderForNormalMovie;

                // Check if an existing view can be reused, otherwise inflate new view for row
                if (recycledViewType != itemViewType) {
                    // Inflate new view
                    convertView = getInflatedLayoutForType(itemViewType);

                    viewHolderForNormalMovie = new ViewHolderForNormalMovie(convertView);

                    convertView.setTag(viewHolderForNormalMovie);
                } else {
                    // Re-use recycled view
                    viewHolderForNormalMovie = (ViewHolderForNormalMovie) convertView.getTag();
                }

                bindViewHolderForNormalMovie(position, viewHolderForNormalMovie);

                return convertView;

            case 1:
                ViewHolderForPopularMovie viewHolderForPopularMovie;

                // Check if an existing view can be reused, otherwise inflate new view for row
                if (recycledViewType != itemViewType) {
                    // Inflate new view
                    convertView = getInflatedLayoutForType(itemViewType);

                    viewHolderForPopularMovie = new ViewHolderForPopularMovie(convertView);

                    convertView.setTag(viewHolderForPopularMovie);
                } else {
                    // Re-use recycled view
                    viewHolderForPopularMovie = (ViewHolderForPopularMovie) convertView.getTag();
                }

                bindViewHolderForPopularMovie(position, viewHolderForPopularMovie);

                return convertView;

            default:
                throw new IllegalArgumentException("itemViewType is wrong value");
        }
    }


    private void bindViewHolderForNormalMovie(int position, ViewHolderForNormalMovie viewHolder) {
        Movie movie = movies.get(position);

        if(viewHolder.tvTitle != null) {
            viewHolder.tvTitle.setText(movie.getTitle());
        }

        if(viewHolder.tvOverview != null) {
            viewHolder.tvOverview.setText(movie.getOverview());
        }

        int orientation = getContext().getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            loadImage(viewHolder.ivPoster, movie.getPosterPath());
        } else {
            loadImage(viewHolder.ivBackdrop, movie.getBackdropPath());
        }

    }

    public void bindViewHolderForPopularMovie(int position, ViewHolderForPopularMovie viewHolder) {
        Movie movie = movies.get(position);
        loadImage(viewHolder.ivBackdrop, movie.getBackdropPath());
    }

    private void loadImage(ImageView imageView, String path) {
        String imageUrl = "http://image.tmdb.org/t/p/w500" + path;

        if(imageView != null) {
            Picasso.with(getContext())
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_loading)
                    .into(imageView);
        }
    }

    // Returns the number of types of Views that will be created by getView(int, View, ViewGroup)
    @Override
    public int getViewTypeCount() {
        // Returns the number of types of Views that will be created by this adapter
        // Each type represents a set of views that can be converted
        return 2;
    }

    // Get the type of View that will be created by getView(int, View, ViewGroup)
    // for the specified item.
    @Override
    public int getItemViewType(int position) {
        // Return an integer here representing the type of View.
        // Note: Integers must be in the range 0 to getViewTypeCount() - 1
        Movie movie = getItem(position);
        if(movie.getVoteAverage() >= 5) { // movie with vote average more than 5 starts
            return 1;
        } else {
            return 0;
        }
    }

    public View getInflatedLayoutForType(int type) {
        switch (type) {
            case 0:
                return LayoutInflater.from(getContext()).inflate(R.layout.normal_movie_in_list, null);
            case 1:
                return LayoutInflater.from(getContext()).inflate(R.layout.popular_movie_in_list, null);
            default:
                return null;
        }
    }

    public int getRecycledViewType(View view) {
        if(view != null) {
            switch (view.getId()) {
                case R.id.normal_movie_in_list:
                    return 0;
                case R.id.popular_movie_in_list:
                    return 1;
                default:
                    return -1;
            }
        } else {
            return -1;
        }
    }
}
