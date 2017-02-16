package com.duphungcong.flicksclone.adpaters;

import android.content.Context;
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
    // View lockup cache
    private static class ViewHolder {
        TextView tvMovieTitle;
        ImageView ivPosterImage;
    }

    public MoviesAdapter(Context context, List<Movie> objects) {
        super(context, R.layout.movie_in_list, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate new view for row
        ViewHolder viewHolder;
        if(convertView == null) {
            // If there is now view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_in_list, parent, false);
            viewHolder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tvMovieTitle);
            viewHolder.ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvMovieTitle.setText(movie.getTitle());
        String imageUrl = "http://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Picasso.with(getContext())
                .load(imageUrl)
                .resize(500, 500)
                .centerCrop()
                .error(R.drawable.deadpool_poster)
                .into(viewHolder.ivPosterImage);

        return convertView;
    }
}
