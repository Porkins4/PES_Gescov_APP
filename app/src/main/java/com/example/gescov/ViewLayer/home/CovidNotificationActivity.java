package com.example.gescov.ViewLayer.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gescov.R;
import com.example.gescov.Singletons.CurrentContext;
import com.example.gescov.ViewLayer.PresentationControlFactory;



public class CovidNotificationActivity extends AppCompatActivity {
    private  Context thisContext;
    private NotifyContagionController notifyContagion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_notification);
        thisContext = this;
        notifyContagion =  PresentationControlFactory.getNotifyContagionController();
        if (getIntent().hasExtra("NameInfected")) {
            String name = getIntent().getExtras().getString("NameInfected");
            String school = getIntent().getExtras().getString("School");
        }
        CurrentContext.setContext(thisContext);
        Button notify = (Button) findViewById(R.id.notifypositive);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean success = notifyContagion.notifyInfected();
                if (!success) OpenError();
            }
        });
    }

    private void OpenError() {
        PopErrorNotifyPositive error = new PopErrorNotifyPositive();
        error.show(getSupportFragmentManager(),"Tag");
    }
}