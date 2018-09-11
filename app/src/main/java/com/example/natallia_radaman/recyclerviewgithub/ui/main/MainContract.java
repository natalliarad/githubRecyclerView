package com.example.natallia_radaman.recyclerviewgithub.ui.main;

import com.example.natallia_radaman.recyclerviewgithub.domain.Repository;

import java.util.List;

public interface MainContract {
    interface  Model{
        void repositoriesSearch(int page);
    }

    interface View{
        void repositoriesShow(List<Repository> repositoriosList);
        void repositoryConnect(Repository repositorio);
        void progressBarShow(boolean show);
    }

    interface Presenter{
        void requestRepositories(int page);
        void loadRepositories(List<Repository> listOfRepositories);
    }
}
