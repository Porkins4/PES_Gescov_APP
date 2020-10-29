package com.example.gescov.ViewLayer.MarkPositionInClassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.R;
import com.example.gescov.Singletons.ActualContext;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MarkPositionInClassroom extends AppCompatActivity {

    private Button button;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_position_in_classroom);
        context = this;
        button = (Button) findViewById(R.id.mark_position_in_classroom_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MarkPositionInClassroomController controller = PresentationControlFactory.getMarkPositionInClassroomController();
                        ActualContext.setContext(context);
                        controller.sendReservationRequest("aula",getIntent().getExtras().getInt("row"),getIntent().getExtras().getInt("col"));
                        finish();
                    }
                }
        );
    }
}