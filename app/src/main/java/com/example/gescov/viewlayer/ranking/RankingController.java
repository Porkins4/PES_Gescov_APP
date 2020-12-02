package com.example.gescov.viewlayer.ranking;

import android.util.Pair;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class RankingController {
    private RankingViewModel rankingViewModel;


    public void sendResponseOfNumContagionPerSchool(List<Pair<School, Integer>> schools) {
        rankingViewModel.setResponse(schools);

    }
    public void setViewModel(RankingViewModel rankingViewModel) {
        this.rankingViewModel = rankingViewModel;
    }

    public void getRanking() {
        PresentationControlFactory.getViewLayerController().getNumContagionPerSchool(2);
    }
}
