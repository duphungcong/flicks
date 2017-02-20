package com.duphungcong.flicksclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.duphungcong.flicksclone.R;
import com.duphungcong.flicksclone.adpaters.MoviesAdapter;
import com.duphungcong.flicksclone.models.Movie;
import com.duphungcong.flicksclone.models.MoviesResponse;
import com.duphungcong.flicksclone.network.ApiClient;
import com.duphungcong.flicksclone.network.ApiEndpointInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView moviesList;
    private ArrayAdapter<Movie> moviesAdapter;
    private List<Movie> movies;
    private SwipeRefreshLayout swipeContainer;

    private static final String TAG = MainActivity.class.getSimpleName();
    private String API_KEY = "8870acc15c4c34351838d4c68793a8d1";
    private int currentPage = 1;
    private int previousPage = 0;
    private int totalPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        moviesList = (ListView) findViewById(R.id.lvMoviesList);
        movies = new ArrayList<>();

        // Support swipe to refresh the view
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesAdapter.clear();
                getNowPlayingMovies(currentPage);
                swipeContainer.setRefreshing(false);
            }
        });

        // Get now playing movies from themoviedb.org and fill into List View
        getNowPlayingMovies(currentPage);

        // Listen evens on list view
        setupLisViewListener();
    }

    public void getNowPlayingMovies(int page) {
        if(API_KEY.isEmpty()) {
            Toast.makeText(this, "No API KEY", Toast.LENGTH_LONG).show();
            return;
        }

        ApiEndpointInterface apiService = ApiClient.getClient().create(ApiEndpointInterface.class);

        Call<MoviesResponse> call = apiService.getNowPayingMovies(API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies = response.body().getResults();
                totalPages = response.body().getTotalPages();
                Log.d(TAG, "request now playing movies");

                moviesAdapter = new MoviesAdapter(getApplicationContext(), movies);
                moviesList.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setupLisViewListener() {
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);

                intent.putExtra("movie", moviesAdapter.getItem(position));

                startActivity(intent);
            }
        });

        moviesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
