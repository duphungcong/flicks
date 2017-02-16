package com.duphungcong.flicksclone.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.duphungcong.flicksclone.R;
import com.duphungcong.flicksclone.adpaters.MoviesAdapter;
import com.duphungcong.flicksclone.network.ApiClient;
import com.duphungcong.flicksclone.network.ApiEndpointInterface;
import com.duphungcong.flicksclone.models.Movie;
import com.duphungcong.flicksclone.models.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView moviesList;
    private ArrayAdapter<Movie> moviesAdapter;
    private List<Movie> movies;

    private static final String TAG = MainActivity.class.getSimpleName();
    private String API_KEY = "8870acc15c4c34351838d4c68793a8d1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNowPlayingMovies();
    }

    public void getNowPlayingMovies() {
        if(API_KEY.isEmpty()) {
            Toast.makeText(this, "No API KEY", Toast.LENGTH_LONG).show();
            return;
        }

        ApiEndpointInterface apiService = ApiClient.getClient().create(ApiEndpointInterface.class);

        Call<MoviesResponse> call = apiService.getNowPayingMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies = new ArrayList<>();
                movies = response.body().getResults();
                Log.d(TAG, "request now playing movies");

                moviesAdapter = new MoviesAdapter(getApplicationContext(), movies);
                moviesList = (ListView) findViewById(R.id.lvMoviesList);
                moviesList.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
