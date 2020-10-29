package com.example.gescov.ViewLayer.MarkPositionInClassroom;

import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.ViewLayerController;

public class MarkPositionInClassroomController {

    public void sendReservationRequest(String aula, int row, int col) {
        ViewLayerController viewLayerController = PresentationControlFactory.getViewLayerController();
        viewLayerController.sendReservationRequest(aula,row,col);
    }
}
