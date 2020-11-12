package com.example.gescov.ViewLayer.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class DailyTestActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    List<Boolean> answers;
    TracingTestController tracingTestController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_test);
        tracingTestController = PresentationControlFactory.getTracingTestControllerController();
        answers = new ArrayList<>();
        Button sendTest = findViewById(R.id.sendTest);

        sendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillAnswers();
                tracingTestController.sendAnswers(answers);
            }
        });
    }


    public Boolean checkButton(RadioGroup radiogroup) {
        int radioId = radiogroup.getCheckedRadioButtonId();
        RadioButton radiobutton = findViewById(radioId);
        return radiobutton.getText().toString() == "Si";
    }


    public void fillAnswers() {
        radioGroup = findViewById(R.id.firstGroup);
        answers.add(checkButton(radioGroup));
        radioGroup = findViewById(R.id.secondGroup);
        answers.add(checkButton(radioGroup));
        radioGroup = findViewById(R.id.thirdGroup);
        answers.add(checkButton(radioGroup));
        radioGroup = findViewById(R.id.fourthGroup);
        answers.add(checkButton(radioGroup));
        radioGroup = findViewById(R.id.fifthGroup);
        answers.add(checkButton(radioGroup));
    }
}