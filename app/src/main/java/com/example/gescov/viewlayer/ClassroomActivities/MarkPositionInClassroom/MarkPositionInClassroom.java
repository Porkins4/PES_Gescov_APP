package com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution.ClassroomDsitributionViewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class MarkPositionInClassroom extends AppCompatActivity {

    private Button button;
    private ClassroomDsitributionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_position_in_classroom);
        initComponents();
        button = (Button) findViewById(R.id.mark_position_in_classroom_button);
        button.setOnClickListener(v -> reservationRequest());
    }

    private void reservationRequest() {
        viewModel.reservationRequest().observe(this,
                error -> {
                    Intent resultIntent = new Intent();
                    if (error) {
                        if (viewModel.getErrorCode() == 500) PresentationControlFactory.getMessagesManager().toastMessage(R.string.already_has_reservation);
                        else PresentationControlFactory.getMessagesManager().toastMessage(R.string.general_error_message);
                        button.setEnabled(false);
                        setResult(RESULT_CANCELED, resultIntent);
                    } else {
                        PresentationControlFactory.getMessagesManager().toastMessage(R.string.succesfull_reservation);
                        setResult(RESULT_OK,resultIntent);
                        finish();
                    }
                });
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(ClassroomDsitributionViewModel.class);
        viewModel.init(getIntent().getExtras().getString("classSessionID"),getIntent().getExtras().getInt("row")+1,getIntent().getExtras().getInt("col")+1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.mark_position_in_classroom_title);
    }



}