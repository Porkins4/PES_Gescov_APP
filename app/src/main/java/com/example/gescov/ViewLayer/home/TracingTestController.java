package com.example.gescov.ViewLayer.home;

import com.example.gescov.ViewLayer.PresentationControlFactory;

import java.util.List;

public class TracingTestController {
    public void sendAnswers(List<Boolean> answers) {
        PresentationControlFactory.getViewLayerController().sendAnswers(answers);
    }
}
