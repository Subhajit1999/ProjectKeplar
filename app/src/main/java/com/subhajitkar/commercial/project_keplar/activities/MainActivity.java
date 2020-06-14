package com.subhajitkar.commercial.project_keplar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.subhajitkar.commercial.project_keplar.R;
import com.subhajitkar.commercial.project_keplar.utils.MenuAdapter;
import com.subhajitkar.commercial.project_keplar.utils.StaticUtils;

import java.util.ArrayList;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private Activity mActivity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //methods call
        handleNavigationDrawer();
        handleNavigationMenu();
    }

    private void handleNavigationDrawer(){
        Log.d(TAG, "handleNavigationDrawer: handling navigation drawer");
        final DuoDrawerLayout drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.d(TAG, "onDrawerSlide: drawer sliding. Offset: "+slideOffset);
                //drawer headerView widgets initialization
                if (slideOffset>0.5) {
                    StaticUtils.changeStatusBarColor(mActivity, R.color.light_yellow);
                }else{
                    StaticUtils.changeStatusBarColor(mActivity, R.color.colorPrimaryDark);
                }
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void handleNavigationMenu(){
        Log.d(TAG, "handleNavigationMenu: handling navigation menu");
        DuoMenuView duoMenuView = (DuoMenuView) findViewById(R.id.menu);
        duoMenuView.setBackgroundResource(R.drawable.bg_gradient);
        ArrayList<String> options = new ArrayList<>();
        options.add("Home");
        options.add("Bookmark");
        options.add("Settings");
        options.add("About");
        final MenuAdapter adapter = new MenuAdapter(options);
        duoMenuView.setAdapter(adapter);
        adapter.setViewSelected(0, true);  //home selected, by default

        duoMenuView.setOnMenuClickListener(new DuoMenuView.OnMenuClickListener() {
            @Override
            public void onFooterClicked() {
                Log.d(TAG, "onFooterClicked: footer clicked");
            }

            @Override
            public void onHeaderClicked() {
                Log.d(TAG, "onHeaderClicked: header clicked");
            }

            @Override
            public void onOptionClicked(int position, Object objectClicked) {
                // Set the right options selected
                Log.d(TAG, "onOptionClicked: options clicked");
                adapter.setViewSelected(position, true);
                topNavSelectedAction(position);
            }
        });
    }

    private void topNavSelectedAction(int clickedPosition){
        Log.d(TAG, "topNavSelectedAction: nav drawer option clicked action");
    }
}