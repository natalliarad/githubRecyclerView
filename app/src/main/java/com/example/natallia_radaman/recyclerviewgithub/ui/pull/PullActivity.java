package com.example.natallia_radaman.recyclerviewgithub.ui.pull;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.natallia_radaman.recyclerviewgithub.R;
import com.example.natallia_radaman.recyclerviewgithub.adapter.PullAdapter;
import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the pull list of the selected author repository.
 */
public class PullActivity extends AppCompatActivity implements PullContract.View{
    private PullAdapter pullAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private List<Pull> listOfPulls;
    private String author;
    private String repository;
    private PullPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        repository = getIntent().getStringExtra("repository");
        author = getIntent().getStringExtra("author");

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        progressBar = findViewById(R.id.progressBar);
        presenter = new PullPresenter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        listOfPulls = new ArrayList<>();

        if (savedInstanceState != null){
            listOfPulls = savedInstanceState.getParcelableArrayList("pullList");
        }

        pullAdapter = new PullAdapter(listOfPulls, this, presenter);
        recyclerView.setAdapter(pullAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listOfPulls != null && !(listOfPulls.size() > 0))
            presenter.searchPulls(author,repository);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listOfPulls", (ArrayList<? extends Parcelable>) listOfPulls);
    }

    @Override
    public void showPulls(List<Pull> pull) {
        listOfPulls.clear();
        listOfPulls.addAll(pull);
        pullAdapter.notifyDataSetChanged();
    }

    @Override
    public void connectPull(Pull pull) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pull.url));
        startActivity(browserIntent);
    }

    @Override
    public void showProgress(Boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}
