package com.example.gescov.viewlayer.home;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;


public class CovidNotificationActivity extends AppCompatActivity {
    private NotifyContagionController notifyContagionState;
    private CovidNotificationViewModel covidNotificationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_notification);
        init();
        covidNotificationViewModel = new ViewModelProvider(this).get(CovidNotificationViewModel.class);
        covidNotificationViewModel.getPostResult().observe(this, new Observer<ContagionRequestResult>() {
            @Override
            public void onChanged(ContagionRequestResult contagionRequestResult) {

                Pair<String,Boolean> response = contagionRequestResult.getError();
                if (response.first.equals("notifyPositive")) {
                    if (response.second ) {
                        OpenPopup("notifyPositive");
                    }
                    else {
                        succesfulContagionRequest(contagionRequestResult);
                    }
                }
                else if (response.first.equals("notifyRecovery")) {
                    if (response.second ) {
                        OpenPopup("notifyRecovery");
                    }
                    else {
                        succesfulRecovery();
                    }
                }
                else if (response.first.equals("notifyPossiblePositive")) {
                    if ( response.second ) {
                        OpenPopup("notifyPossiblePositive");
                    }
                    else {
                        sucessfulPossibleContagionRequest(contagionRequestResult);
                    }
                }
            }
        });
        notifyContagionState =  PresentationControlFactory.getNotifyContagionController();
    }

    private void sucessfulPossibleContagionRequest(ContagionRequestResult contagionRequestResult) {
        Toast.makeText(this,R.string.succesPossiblePositiveNotification,Toast.LENGTH_SHORT).show();
    }

    private void succesfulRecovery() {
        Toast.makeText(this,R.string.succesRecoveryNotification,Toast.LENGTH_SHORT).show();
    }

    private void succesfulContagionRequest(ContagionRequestResult contagionRequestResult) {
        Toast.makeText(this,R.string.succesPositiveNotification,Toast.LENGTH_SHORT).show();
    }

    private void initButtonNotifyPositive() {
        Button notifyPositive = findViewById(R.id.notifypositive);
        notifyPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidNotificationViewModel.getNotifyInfectedResult();
            }
        });
    }

    private void init() {
        initRecoverButton();
        initButtonNotifyPositive();
        initPossiblePositive();
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.covid_notification_title);
    }

    private void initPossiblePositive() {
        Button notifypossible = findViewById(R.id.notifypossible);
        notifypossible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidNotificationViewModel.notifyPossibleContagion();
            }
        });
    }

    private void initRecoverButton() {
        Button notifyRecover = findViewById(R.id.notifyrecover);
        notifyRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidNotificationViewModel.notifyRecoveryResult();
            }
        });
    }

    private void OpenPopup(String notification) {
        if (notification == "notifyPositive" || notification == "notifyPossiblePositive" ){
            PopErrorNotifyPositive error = new PopErrorNotifyPositive();
            error.show(getSupportFragmentManager(),"Tag");
        }
        else {
            PopErrorNotifyRecovery error = new PopErrorNotifyRecovery();
            error.show(getSupportFragmentManager(),"Tag");
        }
    }
}