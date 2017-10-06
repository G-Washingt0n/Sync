package com.gnyblecraft.marcul.ideasproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.Registry.LogInActivity;
import com.gnyblecraft.marcul.ideasproject.Registry.SignUpActivity;

public class ExploreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static SharedPreferences preferences;

    public static final String SHARED_PREFS_NAME = "filename";
    public static final String TOKEN_NAME = "Token";
    public static final String USER_NAME = "Login";
    public static final String USER_EMAIL = "Email";

    private boolean explore;
    private int counter = 0;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_unlogged);


        preferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);


            try {  // автологин пользователя
                if(!preferences.getString(TOKEN_NAME, null).isEmpty())
                    setContentView(R.layout.activity_explore_loggedin);
                Log.e("Check Token: ", "token exists!!:");
                TextView user = (TextView) findViewById(R.id.nav_header_username);
                user.setText(preferences.getString(USER_NAME,null)); // ошибка где-то тут
                Log.e("Check User: ", user.getText().toString());
                TextView email = (TextView) findViewById(R.id.nav_header_usermail);
                email.setText(preferences.getString(USER_EMAIL,null));
                Log.e("Check Email: ", email.getText().toString());
                // preferences.edit()    .clear()    .apply();//удаление токена для проверки
            } catch (Exception e) {
                Log.e("Check Token: ", "problems with token check!!");
            }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
       /* getMenuInflater().inflate(R.menu.explore, menu);
        return true;*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
            } else if (id == R.id.Explore) {
                counter++;
                if(counter%2==1)
                    explore = true;
                else
                    explore = false;
                onPrepareOptionsMenu(menu);
            }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //super.onPrepareOptionsMenu(menu);
       /* if(explore)
            menu.findItem(R.id.exploreGroup).setVisible(true);
            //menu.setGroupEnabled(R.id.exploreGroup,true);
        else
            menu.findItem(R.id.exploreGroup).setVisible(false);
            //menu.setGroupEnabled(R.id.exploreGroup,false);
*/
        return true;
    }

    public static void setPreferences(String key, String value){
        preferences.edit()
                .putString(key, value)
                .apply();

    }


}
