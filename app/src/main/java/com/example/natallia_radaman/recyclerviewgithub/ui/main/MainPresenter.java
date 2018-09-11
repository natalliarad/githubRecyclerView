package com.example.natallia_radaman.recyclerviewgithub.ui.main;

import com.example.natallia_radaman.recyclerviewgithub.adapter.RepositoryAdapter;
import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;
import com.example.natallia_radaman.recyclerviewgithub.model.RepositoryModel;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, RepositoryAdapter.OnItemClickListener {
    private MainContract.View view;
    private MainContract.Model model;

    public MainPresenter(MainContract.View view){
        this.view = view;
        model = new RepositoryModel(this);
    }


    @Override
    public void requestRepositories(int page) {
        view.progressBarShow(true);
        model.repositoriesSearch(page);
    }

    @Override
    public void onItemClick(Repository repository) {
        view.repositoryConnect(repository);
    }

    @Override
    public void loadRepositories(List<Repository> listOfRepositories) {
        view.repositoriesShow(listOfRepositories);
        view.progressBarShow(false);
    }
}
