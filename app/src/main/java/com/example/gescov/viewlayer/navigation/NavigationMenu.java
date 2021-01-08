package com.example.gescov.viewlayer.navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SignUpAndLogin.LoginActivity;
import com.example.gescov.viewlayer.Singletons.GescovApplication;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationMenu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private CircleImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.hide();
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        initMenuUserData(navigationView);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.school_administration,R.id.chat_list,R.id.user_all_schools,R.id.user_schools,R.id.map_id,R.id.ranking_fragment,R.id.menu_subjects,R.id.menu_forum)

                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        MenuItem logoutButton = navigationView.getMenu().findItem(R.id.logout);
        MenuItem openCalendar = navigationView.getMenu().findItem(R.id.open_calendar);
        MenuItem schoolAdministration = navigationView.getMenu().findItem(R.id.school_administration);
        logoutButton.setOnMenuItemClickListener(e -> {
            logoutPrompt();
            return true;
        });

        openCalendar.setOnMenuItemClickListener(e-> {
            openCalendar();
            return true;
        });

        /*schoolAdministration.setOnMenuItemClickListener(e-> {
            if (!GescovUtils.isUserAdminInAnySchool(PresentationControlFactory.getLoadingProfileController().getLoggedInUser())) {
                PresentationControlFactory.getMessagesManager().toastMessage(R.string.user_not_admin);
            } else {
                navController.navigate(R.id.school_administration);
                drawer.closeDrawers();
            }
            return true;
        });*/
    }

    private void initMenuUserData(NavigationView navigationView) {
        TextView email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email_view);
        email.setText(PresentationControlFactory.getViewLayerController().getUserEmail());
        TextView user_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        userImage = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);

        User loggedUser = PresentationControlFactory.getViewLayerController().getUserLoggedIn();
        user_name.setText(loggedUser.getName());
        loadImageFromUrl(loggedUser.getPic());
    }

    private void loadImageFromUrl(String pic) {
        Picasso.with(GescovApplication.getContext()).load(pic).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).noFade().into(userImage, new com.squareup.picasso.Callback() {
            public void onSuccess() {
                //it returns nothing
            }
            @Override
            public void onError() {
                Log.i("loadingImage","error on loading image");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void openCalendar() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(intent.CATEGORY_APP_CALENDAR);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void logoutFunc() {
        deleteUserToken();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), R.string.logout_successful, Toast.LENGTH_LONG).show();
                        showLoginView();
                    }
                });
    }

    private void deleteUserToken() {
        PresentationControlFactory.getViewLayerController().deleteUserToken(GescovApplication.getNotificationToken());
    }

    private void logoutPrompt() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) logoutFunc();
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirm_logout)).setPositiveButton(getString(R.string.confirm), dialogClickListener)
                .setNegativeButton(getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void showLoginView() {
        String logout = getString(R.string.logout_successful);
        Toast.makeText(getApplicationContext(), logout, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}