package com.example.gescov.viewlayer.SignUpAndLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final int SUCCESSDUL_SIGN_IN = 200;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initGoogleButton();
        initAppBar();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initAppBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    private void initGoogleButton() {
        Button signInButton = (Button) findViewById(R.id.sign_in_button_login_activity);
        signInButton.setOnClickListener(v -> {
            Intent signInIntent = LoadingProfileController.getGoogleSignInClient(getString(R.string.server_client_id)).getSignInIntent();
            startActivityForResult(signInIntent, SUCCESSDUL_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESSDUL_SIGN_IN && (GoogleSignIn.getLastSignedInAccount(this)) != null) {
            //SignUp(GoogleSignIn.getLastSignedInAccount(this).getEmail(),"flkasdfnalfnak");
            Intent i = new Intent(this, LoadingProfileActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAppBar();
    }

    public void SignUp(String email, String id) {
        mAuth.createUserWithEmailAndPassword(email,id)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    System.out.println("se registró");
                                } else {
                                    System.out.println("lol, falló");
                                }
                            }
                        });
    }
}