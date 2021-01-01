package com.example.gescov.viewlayer.home;

import com.example.gescov.domainlayer.Classmodels.TracingTest;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.tracingTestResult.TracingTestViewModel;

import java.util.List;

public class TracingTestController {

    TracingTestViewModel tracingTestViewModel;
    public void sendAnswers(List<Boolean> answers) {
        PresentationControlFactory.getViewLayerController().sendAnswers(answers);
    }

    public void setViewRankingModel(TracingTestViewModel tracingTestViewModel) {
        this.tracingTestViewModel = tracingTestViewModel;
    }

    public void getResults(String userID) {
        PresentationControlFactory.getViewLayerController().getResults(userID);
    }

    public void sendTestAnswers(List<TracingTest> results) {
        tracingTestViewModel.sendTestAnswers(results);
    }
}
