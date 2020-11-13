package com.example.gescov.ViewLayer.home;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.Singletons.CurrentContext;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import java.util.ArrayList;
import java.util.List;



public class DailyTestActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    List<Boolean> answers;
    RadioButton buttonIngroup;
    TracingTestController tracingTestController;
    private Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_test);
        thisContext = this;
        CurrentContext.setContext(thisContext);
        tracingTestController = PresentationControlFactory.getTracingTestControllerController();
        answers = new ArrayList<>();
        Button sendTest = findViewById(R.id.sendTest);
        sendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillAnswers();
                tracingTestController.sendAnswers(answers);
                Toast.makeText(thisContext, "Respostes enviades :)", Toast.LENGTH_LONG).show();
                finish();
            }
        });
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