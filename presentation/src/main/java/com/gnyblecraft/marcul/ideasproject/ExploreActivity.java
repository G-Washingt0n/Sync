package com.gnyblecraft.marcul.ideasproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.Registry.LogInActivity;
import com.gnyblecraft.marcul.ideasproject.Registry.SignUpActivity;
import com.gnyblecraft.marcul.ideasproject.sideNavigationMenu.AboutUsFragment;
import com.gnyblecraft.marcul.ideasproject.sideNavigationMenu.ExploreFragment;
import com.gnyblecraft.marcul.ideasproject.userProfile.ProfileFragment;
import com.gnyblecraft.marcul.ideasproject.userProfile.editProfile.EditProfileActivity;

public class ExploreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static SharedPreferences preferences;

    public static final String SHARED_PREFS_NAME = "filename";
    public static final String TOKEN_NAME = "Token";
    public static final String USER_NAME = "Name";
    public static final String USER_SURNAME = "Surname";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PHOTO_URL = "Photo";
    public static final String USER_ID = "Id";
    public static final String USER_TYPE = "User_type";


    private String hostname = "https://save-ideas.com/api";

    public Toolbar toolbar_explore;
    public Toolbar toolbar_aboutUs;
    public Toolbar toolbar_profile;

    //private  View group;
    private boolean explore;
    private int counter = 0;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView;

//        group = (View) findViewById(R.id.group);

        preferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);


            try {  // автологин пользователя
                if(!preferences.getString(TOKEN_NAME, null).isEmpty())
                    setContentView(R.layout.activity_explore_loggedin);

                navigationView = (NavigationView) findViewById(R.id.nav_view_logged);
                View navigationHeader = navigationView.inflateHeaderView(R.layout.nav_loggedin_explore);

                Log.e("Check Token: ", "token exists!!:");
                ImageView image = (ImageView) navigationHeader.findViewById(R.id.nav_header_userimage);
                //image.setImageBitmap();
                //загрузить фото по url preferences.getString(USER_PHOTO_URL,"");
                /*String url = "https://graph.facebook.com/" + preferences.getString(USER_ID,"") + "/picture";
                String url1 = "data:image/jpeg;base64,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" ;//preferences.getString(USER_PHOTO_URL,"");
                Picasso.with(ExploreActivity.this)
                        .load(url1)
                        .into(image);
                */image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToProfile();
                    }
                });


                ImageView redactor = (ImageView) findViewById(R.id.userImage);
                redactor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ExploreActivity.this, EditProfileActivity.class);
                        startActivity(intent);
                        finish();
                        //+анимашка снизу вверх
                    }
                });

                TextView user = (TextView) navigationHeader.findViewById(R.id.nav_header_username);
                user.setText(preferences.getString(USER_NAME,"") + " " + preferences.getString(USER_SURNAME,""));
                user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToProfile();
                        }
                });

                TextView email = (TextView) navigationHeader.findViewById(R.id.nav_header_usermail);
                email.setText(preferences.getString(USER_EMAIL,""));
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToProfile();
                    }
                });
                // preferences.edit()    .clear()    .apply();//удаление токена для проверки
            } catch (Exception e) {
                setContentView(R.layout.activity_explore_unlogged);
                navigationView  = (NavigationView) findViewById(R.id.nav_view_unlogged);
                Log.e("Check Token: ", "problems with token check!!");
            }

        try{
            Intent intent = getIntent();
            if(!intent.getStringExtra("show profile").isEmpty())
                goToProfile();
        } catch(Exception e){}
        toolbar_explore = (Toolbar) findViewById(R.id.toolbar_explore);
        toolbar_aboutUs = (Toolbar) findViewById(R.id.toolbar_about_us);
        toolbar_aboutUs.setVisibility(View.GONE);
        toolbar_profile = (Toolbar) findViewById(R.id.toolbar_profile);
        toolbar_profile.setVisibility(View.GONE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar_explore, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void goToProfile(){
        toolbar_explore.setVisibility(View.GONE);
        toolbar_aboutUs.setVisibility(View.GONE);
        toolbar_profile.setVisibility(View.VISIBLE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(ExploreActivity.this,drawer,toolbar_profile,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        showFragment(getSupportFragmentManager(),new ProfileFragment());
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.menu = menu;
        getMenuInflater().inflate(R.menu.activity_unlogged_explore_drawer, menu);
        return true; // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.explore, menu);
        // return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.LogIn) {

            Intent intent = new Intent(ExploreActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.SignUp) {
            Intent intent = new Intent(ExploreActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.LogOutInMenu) {    // ВРЕМЕННО! логаут для проверки регистрации
            preferences.edit()
                        .clear()
                        .apply();

            Intent intent = new Intent(ExploreActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.Explore) {
            toolbar_profile.setVisibility(View.GONE);
            toolbar_aboutUs.setVisibility(View.GONE);
            toolbar_explore.setVisibility(View.VISIBLE);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar_explore, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            showFragment(getSupportFragmentManager(), new ExploreFragment());
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.About_us) {
            toolbar_profile.setVisibility(View.GONE);
            toolbar_explore.setVisibility(View.GONE);
            toolbar_aboutUs.setVisibility(View.VISIBLE);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar_aboutUs, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            showFragment(getSupportFragmentManager(),new AboutUsFragment());
            drawer.closeDrawer(GravityCompat.START);

        } else if(id == R.id.nav_header_userimage){
            toolbar_explore.setVisibility(View.GONE);
            toolbar_aboutUs.setVisibility(View.GONE);
            toolbar_profile.setVisibility(View.VISIBLE);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar_profile, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            showFragment(getSupportFragmentManager(),new ProfileFragment());
            drawer.closeDrawer(GravityCompat.START);

            // goToProfile(preferences.getString(TOKEN_NAME,""));

        } /*else if(id == R.id.Information) {

            group.setVisibility(View.VISIBLE);
        }     else if (id == R.id.nav_send) {

        }*/


        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START); //убрать шторку меню
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public static void setPreferences(String key, String value){
        preferences.edit()
                .putString(key, value)
                .apply();

    }


    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.explore_content_fragment,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
