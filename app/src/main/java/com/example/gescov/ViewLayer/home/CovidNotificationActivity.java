package com.example.gescov.ViewLayer.home;

import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;



public class CovidNotificationActivity extends AppCompatActivity {
    private CovidNotificationViewModel covidNotificationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_notification);
        init();
        covidNotificationViewModel = new ViewModelProvider(this).get(CovidNotificationViewModel.class);
        covidNotificationViewModel.getPostResult().observe(this, contagionRequestResult -> {
            String notifyRecoveryRes ="notifyRecovery";
            String notifyPositiveRes = "notifyPositive";
            String notifyPossibleRes = "notifyPossiblePositive";

            Pair<String,Boolean> response = contagionRequestResult.getError();
            if (response.first.equals(notifyPositiveRes) ) {
                if (response.second ) {
                    openPopup(notifyPositiveRes);
                }
                else {
                    succesfulContagionRequest();
                }
            }
            else if ( response.first.equals(notifyRecoveryRes) ) {
                if (response.second ) {
                    openPopup(notifyRecoveryRes);
                }
                else {
                    succesfulRecovery();
                }
            }
            else if (response.first.equals(notifyPossibleRes)) {
                if ( response.second ) {
                    openPopup(notifyPossibleRes);
                }
                else {
                    sucessfulPossibleContagionRequest();
                }
            }
        });
    }

    private void sucessfulPossibleContagionRequest() {
        Toast.makeText(this,R.string.succesPossiblePositiveNotification,Toast.LENGTH_SHORT).show();
    }

    private void succesfulRecovery() {
        Toast.makeText(this,R.string.succesRecoveryNotification,Toast.LENGTH_SHORT).show();
    }

    private void succesfulContagionRequest() {
        Toast.makeText(this,R.string.succesPositiveNotification,Toast.LENGTH_SHORT).show();
    }

    private void initButtonNotifyPositive() {
        Button notifyPositive = findViewById(R.id.notifypositive);
        notifyPositive.setOnClickListener(v -> covidNotificationViewModel.getNotifyInfectedResult());
    }

    private void init() {
        initRecoverButton();
        initButtonNotifyPositive();
        initPossiblePositive();
    }

    private void initPossiblePositive() {
        Button notifypossible = findViewById(R.id.notifypossible);
        notifypossible.setOnClickListener(v -> covidNotificationViewModel.notifyPossibleContagion());
    }

    private void initRecoverButton() {
        Button notifyRecover = findViewById(R.id.notifyrecover);
        notifyRecover.setOnClickListener(v -> covidNotificationViewModel.notifyRecoveryResult());
    }

    private void openPopup(String notification) {
        if (notification.equals("notifyPositive")  || notification.equals("notifyPossiblePositive" )){
            PopErrorNotifyPositive error = new PopErrorNotifyPositive();
            error.show(getSupportFragmentManager(),"Tag");
        }
        else {
            PopErrorNotifyRecovery error = new PopErrorNotifyRecovery();
            error.show(getSupportFragmentManager(),"Tag");
        }
    }
}