package com.example.natallia_radaman.recyclerviewgithub.service;

import android.util.Log;

import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitPullService extends GitService {
    private List<Pull> pullList;

    private LoadPullsListener listener;

    public void searchPulls(String author, String repository){
        GitAPI gitApi = retrofit.create(GitAPI.class);
        Call<List<Pull>> pulls = gitApi.listPulls(author,repository);

        pulls.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if (response.isSuccessful()){
                    pullList = response.body();
                    listener.onLoad(pullList);
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {
                Log.e("Application", "Error: " + t.getMessage() );
            }
        });
    }

    public void setListener(LoadPullsListener listener) {
        this.listener = listener;
    }

    public interface LoadPullsListener{
        void onLoad(List<Pull> pullList);
    }
}
