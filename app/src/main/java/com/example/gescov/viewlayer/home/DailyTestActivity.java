package com.example.gescov.viewlayer.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;



public class DailyTestActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    List<Boolean> answers;
    RadioButton buttonIngroup;
    TracingTestController tracingTestController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_test);
        tracingTestController = PresentationControlFactory.getTracingTestController();
        answers = new ArrayList<>();
        Button sendTest = findViewById(R.id.sendTest);
        sendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTests();
            }
        });
    }

    private void sendTests() {
        fillAnswers();
        tracingTestController.sendAnswers(answers);
        Toast.makeText( this, "Respostes enviades :)", Toast.LENGTH_LONG).show();
        finish();
    }


    public Boolean checkButton(RadioGroup radiogroup, RadioButton button) {
        int radioId = radiogroup.getCheckedRadioButtonId();
        System.out.println(radioId);
        RadioButton radiobutton = findViewById(radioId);
        return radiobutton  == button;
    }


    public void fillAnswers() {
        // it can be improved if we put the buttons in a list and the button groups in a list2
        radioGroup = findViewById(R.id.firstGroup);
        buttonIngroup = findViewById(R.id.firstYes);
        answers.add(checkButton(radioGroup,buttonIngroup));

        radioGroup = findViewById(R.id.secondGroup);
        buttonIngroup = findViewById(R.id.secondYes);
        answers.add(checkButton(radioGroup,buttonIngroup));

        radioGroup = findViewById(R.id.thirdGroup);
        buttonIngroup = findViewById(R.id.thirdYes);
        answers.add(checkButton(radioGroup,buttonIngroup));

        radioGroup = findViewById(R.id.fourthGroup);
        buttonIngroup = findViewById(R.id.fourthYes);
        answers.add(checkButton(radioGroup,buttonIngroup));

        radioGroup = findViewById(R.id.fifthGroup);
        buttonIngroup = findViewById(R.id.fifthYes);
        answers.add(checkButton(radioGroup,buttonIngroup));
    }
}