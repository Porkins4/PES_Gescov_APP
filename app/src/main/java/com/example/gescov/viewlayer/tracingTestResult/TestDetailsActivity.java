package com.example.gescov.viewlayer.tracingTestResult;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestDetailsActivity extends AppCompatActivity {
    TextView firstResponse;
    TextView secondResponse;
    TextView thirdResponse;
    TextView fourthResponse;
    TextView fifthResponse;
    CircleImageView userImage;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        initComponents();
        setImage();
        setAnswers(getIntent().getBooleanArrayExtra("answers"));
    }

    private void setImage() {
        String url = getIntent().getStringExtra("picture");
        userImage = findViewById(R.id.user_image);
        loadImageFromUrl(url,userImage);
    }


    private void setAnswers(boolean[] answers) {
        List<String> stringAnswers = new ArrayList<>();
        for(int i = 0; i < answers.length; ++i) {
            if ( answers[i]) stringAnswers.add(getString(R.string.yes));
            else stringAnswers.add(getString(R.string.no));
        }
        firstResponse.setText(stringAnswers.get(0));
        secondResponse.setText(stringAnswers.get(1));
        thirdResponse.setText(stringAnswers.get(2));
        fourthResponse.setText(stringAnswers.get(3));
        fifthResponse.setText(stringAnswers.get(4));
    }

    private void initComponents() {
        firstResponse = findViewById(R.id.response_1);
        secondResponse =findViewById(R.id.response_2);
        thirdResponse =findViewById(R.id.response_3);
        fourthResponse =findViewById(R.id.response_4);
        fifthResponse =findViewById(R.id.response_5);
        userName = findViewById(R.id.name_of_tested);
        userName.setText(getIntent().getStringExtra("userName"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.user_test_response);
    }


    private void loadImageFromUrl(String url, CircleImageView userImage) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).noFade().into(userImage, new com.squareup.picasso.Callback() {
            public void onSuccess() {
                //it returns nothing
            }
            @Override
            public void onError() {
                Log.i("loadingImage","error on loading image");
            }
        });

    }

}