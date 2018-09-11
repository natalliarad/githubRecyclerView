package com.example.natallia_radaman.recyclerviewgithub.ui.pull;

import com.example.natallia_radaman.recyclerviewgithub.adapter.PullAdapter;
import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;
import com.example.natallia_radaman.recyclerviewgithub.model.PullModel;

import java.util.List;

public class PullPresenter implements PullContract.Presenter, PullAdapter.OnItemClickListener{
    private PullContract.View view;
    private PullContract.Model model;

    public PullPresenter(PullContract.View view){
        this.view = view;
        model = new PullModel(this);
    }

    @Override
    public void searchPulls(String author, String repository) {
        view.showProgress(true);
        model.searchPulls(author,repository);
    }

    @Override
    public void loadPulls(List<Pull> pullList) {
        view.showPulls(pullList);
        view.showProgress(false);
    }

    @Override
    public void onItemClick(Pull pull) {
        view.connectPull(pull);
    }
}
