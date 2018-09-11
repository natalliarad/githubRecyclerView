package com.example.natallia_radaman.recyclerviewgithub.service;

import android.util.Log;

import com.example.natallia_radaman.recyclerviewgithub.domain.GitHub;
import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepositoryService extends GitService{
    List<Repository> listOfRepositories;

    RepositoriesLoadListener listener;

    public void searchRepositories(int page){
        GitAPI gitApi = retrofit.create(GitAPI.class);
        Call<GitHub> repositories = gitApi.listReposotories("language:Java","stars",page);

        repositories.enqueue(new Callback<GitHub>() {
            @Override
            public void onResponse(Call<GitHub> call, Response<GitHub> response) {
                if (response.isSuccessful()){
                    listOfRepositories = response.body().items;
                    listener.onLoad(listOfRepositories);
                }
            }

            @Override
            public void onFailure(Call<GitHub> call, Throwable t) {
                Log.e("Application", "Error: " + t.getMessage() );
            }
        });
    }

    public void setListener(RepositoriesLoadListener listener) {
        this.listener = listener;
    }

    public interface RepositoriesLoadListener {
        void onLoad(List<Repository> listOfRepositories);
    }
}
