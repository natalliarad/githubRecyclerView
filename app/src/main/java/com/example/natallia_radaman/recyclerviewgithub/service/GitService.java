package com.example.natallia_radaman.recyclerviewgithub.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitService {
    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GitAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
