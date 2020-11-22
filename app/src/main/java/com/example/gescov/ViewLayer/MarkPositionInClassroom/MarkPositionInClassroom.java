package com.example.gescov.ViewLayer.MarkPositionInClassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;

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
                        controller.sendReservationRequest("aula",getIntent().getExtras().getInt("row"),getIntent().getExtras().getInt("col"));
                        finish();
                    }
                }
        );
    }
}