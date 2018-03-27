package com.cornor.gank.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cornor.gank.R;
import com.cornor.gank.model.OnToTopListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CATEGORY = "Category";
    public static final String GIRLS = "Girls";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> {
            if(curFragment != null && (curFragment instanceof OnToTopListener)){
                (((OnToTopListener) curFragment)).toTop();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        showFragment(AllFragment.newInstance("all"), CATEGORY);
    }

    private void showFragment(Fragment fragment,String tag) {
        curFragment = fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment,tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {
            new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "calendar");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String type = "";
        if (id == R.id.nav_all) {
            type = "all";
        } else if (id == R.id.nav_android) {
            type = "Android";
        } else if (id == R.id.nav_expand) {
            type = "拓展资源";
        } else if (id == R.id.nav_girls) {
            type = "福利";
        } else if (id == R.id.nav_h5) {
            type = "前端";
        } else if (id == R.id.nav_ios) {
            type = "iOS";
        }else if(id == R.id.nav_video){
            type = "休息视频";
        }
        getSupportActionBar().setTitle(item.getTitle());
        if(id == R.id.nav_girls){
            if(!(curFragment instanceof PictureFragment)){
                Fragment fragmentByTag = getFragmentManager().findFragmentByTag(GIRLS);
                if(fragmentByTag != null){
                    showFragment(fragmentByTag, GIRLS);
                }else {
                    showFragment(PictureFragment.newInstance(), GIRLS);
                }
            }
        }else {
            if(!(curFragment instanceof AllFragment)){
                Fragment fragmentByTag = getFragmentManager().findFragmentByTag(CATEGORY);
                if(fragmentByTag != null){
                    showFragment(fragmentByTag, CATEGORY);
                }else {
                    showFragment(AllFragment.newInstance(type),CATEGORY);
                }
            }else {
                ((AllFragment)curFragment).change(type);
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
