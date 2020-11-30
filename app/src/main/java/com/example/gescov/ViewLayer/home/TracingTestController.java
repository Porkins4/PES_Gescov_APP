package com.example.gescov.viewlayer.home;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class TracingTestController {
    public void sendAnswers(List<Boolean> answers) {
        PresentationControlFactory.getViewLayerController().sendAnswers(answers);
    }
}
