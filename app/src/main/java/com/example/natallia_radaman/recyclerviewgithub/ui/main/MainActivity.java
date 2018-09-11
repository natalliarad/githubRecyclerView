package com.example.natallia_radaman.recyclerviewgithub.ui.main;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.natallia_radaman.recyclerviewgithub.R;
import com.example.natallia_radaman.recyclerviewgithub.adapter.RepositoryAdapter;
import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;
import com.example.natallia_radaman.recyclerviewgithub.ui.pull.PullActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the list of the git repositories.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View{
    private LinearLayoutManager linearLayoutManager;
    private RepositoryAdapter repositoryAdapter;
    private Boolean isScrolled = false;
    private int currentRepositoriesItems, totalRepositoriesItems, scrollOutItems;
    private ProgressBar progressBar;
    private List<Repository> repositories;
    private int page;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        repositories = new ArrayList<>();
        page = 1;

        presenter = new MainPresenter(this);

        if (savedInstanceState != null){
            repositories = savedInstanceState.getParcelableArrayList("repositories");
            page = savedInstanceState.getInt("page");
        }

        repositoryAdapter = new RepositoryAdapter(repositories, this, presenter);
        recyclerView.setAdapter(repositoryAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolled = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentRepositoriesItems = linearLayoutManager.getChildCount();
                totalRepositoriesItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if(isScrolled && (currentRepositoriesItems + scrollOutItems == totalRepositoriesItems))
                {
                    isScrolled = false;
                    fetchData();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        outState.putInt("page", page);
        outState.putParcelableArrayList("repositories", (ArrayList<? extends Parcelable>) repositories);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (repositories != null && !(repositories.size() > 0)) {
            presenter.requestRepositories(page);
        }
    }

    private void fetchData() {
        page++;
        presenter.requestRepositories(page);
    }

    @Override
    public void repositoriesShow(List<Repository> repositoriesList) {
        repositories.addAll(repositoriesList);
        repositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void repositoryConnect(Repository repository) {
        Intent intent = new Intent(this, PullActivity.class);
        intent.putExtra("repository", repository.name);
        intent.putExtra("author", repository.author.login);
        startActivity(intent);
    }

    @Override
    public void progressBarShow(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}
