package com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class MarkPositionInClassroom extends AppCompatActivity {

    private Button button;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_position_in_classroom);
        context = this;
        button = (Button) findViewById(R.id.mark_position_in_classroom_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MarkPositionInClassroomController controller = PresentationControlFactory.getMarkPositionInClassroomController();
                        controller.sendReservationRequest(getIntent().getExtras().getString("classSessionID"),getIntent().getExtras().getInt("row")+1,getIntent().getExtras().getInt("col")+1);
                        finish();
                    }
                }
        );
    }
}