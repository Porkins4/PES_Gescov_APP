package com.example.gescov.viewlayer.tracingTestResult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.TracingTest;

import java.util.Objects;

public class TracingTestResultActivity extends AppCompatActivity {

    private ListView listView;
    private TracingTestViewModel tracingTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracing_test_result);
        initComponents();
        getTests();

    }

    private void getTests() {
        String userID = getIntent().getStringExtra("userID");

        tracingTestViewModel = new ViewModelProvider(this).get(TracingTestViewModel.class);
        tracingTestViewModel.getResults(userID).observe(this, received -> {
            if ( received) {
                if ( tracingTestViewModel.empty()) {
                    listView.setVisibility(View.GONE);
                    LinearLayout linearLayout = findViewById(R.id.error);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else {
                    listView.setAdapter(tracingTestViewModel.getAdapter(this));
                    initOnItemClick();
                }
            }
        });

    }

    private void initComponents() {
        listView = findViewById(R.id.tracing_results_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.all_tests);

    }

    private void initOnItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this,TestDetailsActivity.class);
            intent.putExtra("userName",getIntent().getStringExtra("name"));
            TracingTest test =  tracingTestViewModel.getIthElemnt(position);
            boolean[] testAnswers = {test.getAnswers().get(0),test.getAnswers().get(1),test.getAnswers().get(2),test.getAnswers().get(3),test.getAnswers().get(4)};
            intent.putExtra("answers", testAnswers);
            intent.putExtra("picture",getIntent().getStringExtra("picture"));
            startActivity(intent);
        });
    }
}