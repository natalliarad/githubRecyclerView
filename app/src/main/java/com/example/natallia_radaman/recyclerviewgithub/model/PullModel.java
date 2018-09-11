package com.example.natallia_radaman.recyclerviewgithub.model;

import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;
import com.example.natallia_radaman.recyclerviewgithub.service.GitPullService;
import com.example.natallia_radaman.recyclerviewgithub.ui.pull.PullContract;

import java.util.List;

public class PullModel implements PullContract.Model, GitPullService.LoadPullsListener{
    private PullContract.Presenter presenter;

    public PullModel(PullContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onLoad(List<Pull> pullList) {
        presenter.loadPulls(pullList);
    }

    @Override
    public void searchPulls(String author, String repository) {
        GitPullService service = new GitPullService();
        service.setListener(this);
        service.searchPulls(author, repository);
    }
}
