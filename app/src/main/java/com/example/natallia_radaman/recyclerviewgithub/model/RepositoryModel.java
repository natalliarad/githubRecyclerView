package com.example.natallia_radaman.recyclerviewgithub.model;

import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;
import com.example.natallia_radaman.recyclerviewgithub.service.GitRepositoryService;
import com.example.natallia_radaman.recyclerviewgithub.ui.main.MainContract;

import java.util.List;

public class RepositoryModel implements MainContract.Model, GitRepositoryService.RepositoriesLoadListener {
    private MainContract.Presenter presenter;

    public RepositoryModel(MainContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onLoad(List<Repository> listOfRepositories) {
        presenter.loadRepositories(listOfRepositories);
    }

    @Override
    public void repositoriesSearch(int page) {
        GitRepositoryService service = new GitRepositoryService();
        service.setListener(this);
        service.searchRepositories(page);
    }
}
