package com.example.gescov.ViewLayer.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gescov.R;

public class SignInActivity extends AppCompatActivity {

    private TextView welcome_tv;
    private EditText name_te;
    private EditText email_te;
    private EditText password_te;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViewComponents();
    }

    private void initViewComponents() {
        welcome_tv = (TextView) findViewById(R.id.welcome_sign_in);
        name_te = (EditText) findViewById(R.id.name_sing_in);
        email_te = (EditText) findViewById(R.id.email_sign_in);
        password_te = (EditText) findViewById(R.id.password_sign_in);
        register_button = (Button) findViewById(R.id.register_sign_in);
    }
}