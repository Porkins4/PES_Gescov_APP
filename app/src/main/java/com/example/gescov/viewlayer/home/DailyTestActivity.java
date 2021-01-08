package com.example.gescov.viewlayer.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        initToolBar();
        Button sendTest = findViewById(R.id.sendTest);
        sendTest.setOnClickListener(v -> sendTests());
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.dail_test_title);

    }

    private void sendTests() {
        fillAnswers();
        tracingTestController.sendAnswers(answers);
        PresentationControlFactory.getMessagesManager().toastMessage(R.string.daily_test_succesfully_sent_toast);
        finish();
    }


    public Boolean checkButton(RadioGroup radiogroup, RadioButton button) {
        int radioId = radiogroup.getCheckedRadioButtonId();
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