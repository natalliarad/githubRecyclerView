package com.example.natallia_radaman.recyclerviewgithub.service;

import com.example.natallia_radaman.recyclerviewgithub.domain.GitHub;
import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitAPI {
    public static final String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<GitHub> listRepositories(@Query(value = "q", encoded = true) String q, @Query("sort") String sort, @Query("page") int page);

    @GET("repos/{author}/{repository}/pulls")
    Call<List<Pull>> listPulls(@Path("author") String author, @Path("repository") String repository);
}
