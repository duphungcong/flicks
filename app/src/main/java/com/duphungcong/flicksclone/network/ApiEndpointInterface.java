package com.duphungcong.flicksclone.network;

import com.duphungcong.flicksclone.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by udcun on 2/15/2017.
 */

public interface ApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPayingMovies(@Query("api_key") String apiKey, @Query("page") int page);
}
