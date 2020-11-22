package com.example.gescov.ViewLayer.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;


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
                if (response.first == "notifyPositive" ) {
                    if (response.second ) {
                        OpenPopup("notifyPositive");
                    }
                    else {
                        succesfulContagionRequest(contagionRequestResult);
                    }
                }
                else if ( response.first == "notifyRecovery" ) {
                    if (response.second ) {
                        OpenPopup("notifyRecovery");
                    }
                    else {
                        System.out.println("wazzuuuup");
                        succesfulRecovery();
                    }
                }
            }
        });
        notifyContagionState =  PresentationControlFactory.getNotifyContagionController();
    }

    private void succesfulRecovery() {
        Toast.makeText(this,R.string.succesRecoveryNotification,Toast.LENGTH_LONG).show();
        PresentationControlFactory.getNotifyContagionController().setContagionId(null);
    }

    private void succesfulContagionRequest(ContagionRequestResult contagionRequestResult) {
        Toast.makeText(this,R.string.succesPositiveNotification,Toast.LENGTH_LONG).show();
        PresentationControlFactory.getNotifyContagionController().setContagionId(contagionRequestResult.getContagionId());
    }

    private void initButtonNotifyPositive() {
        Button notifyPositive = (Button) findViewById(R.id.notifypositive);
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
    }

    private void initRecoverButton() {
        Button notifyRecover = (Button) findViewById(R.id.notifyrecover);
        notifyRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidNotificationViewModel.notifyRecoveryResult();
            }
        });
    }

    private void OpenPopup(String notification) {
        if (notification == "notifyPositive"){
            PopErrorNotifyPositive error = new PopErrorNotifyPositive();
            error.show(getSupportFragmentManager(),"Tag");
        }
        else {
            PopErrorNotifyRecovery error = new PopErrorNotifyRecovery();
            error.show(getSupportFragmentManager(),"Tag");
        }
    }
}