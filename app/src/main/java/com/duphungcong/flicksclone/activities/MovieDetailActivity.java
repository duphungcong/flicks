package com.duphungcong.flicksclone.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.duphungcong.flicksclone.R;
import com.duphungcong.flicksclone.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by udcun on 2/18/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvReleaseDate, tvOverview;
    private RatingBar rbVote;
    private ImageView ivPoster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getView();

        bindDataToView();
    }

    public void getView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVote = (RatingBar) findViewById(R.id.rbVote);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
    }

    public void bindDataToView() {
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvReleaseDate.setText(movie.getReleaseDate());

        String imageUrl = "http://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Picasso.with(getApplicationContext())
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(ivPoster);

        rbVote.setRating(movie.getVoteAverage() / 2);
        rbVote.setStepSize(0.5f);
    }
}
