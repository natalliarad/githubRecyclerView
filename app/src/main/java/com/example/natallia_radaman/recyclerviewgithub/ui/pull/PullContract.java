package com.example.natallia_radaman.recyclerviewgithub.ui.pull;

import com.example.natallia_radaman.recyclerviewgithub.domain.Pull;

import java.util.List;

public interface PullContract {
    interface Model{
        void searchPulls(String author, String repository);
    }

    interface View{
        void showPulls(List<Pull> pullList);
        void connectPull(Pull pull);
        void showProgress(Boolean show);
    }

    interface Presenter{
        void searchPulls(String author, String repository);
        void loadPulls(List<Pull> pullList);
    }
}
