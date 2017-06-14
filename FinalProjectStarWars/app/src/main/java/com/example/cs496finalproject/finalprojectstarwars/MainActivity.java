package com.example.cs496finalproject.finalprojectstarwars;

import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs496finalproject.finalprojectstarwars.utils.StarWarsUtils;
import com.example.cs496finalproject.finalprojectstarwars.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private StarWarsAdapter adapter;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private RecyclerView recycleView;
    private EditText searchBox;
    private String choice;
    private RecyclerView.Adapter rv_adapter;
    //private List<WeatherInfo> weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (EditText)findViewById(R.id.et_search_box);
        //Drawer Shenagigans
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));



        NavigationView navigationView = (NavigationView)findViewById(R.id.nv_navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        final Button button = (Button) findViewById(R.id.btn_search);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity","Inside Click Listener");
                String searchQuery = searchBox.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doSWSearch(searchQuery, "planets");
                }
            }

        });
    }

    private void doSWSearch(String searchQuery, String choice){
        Log.d("MainActivity","received: " + searchQuery +" and " + choice);
        String swSearch = StarWarsUtils.buildSWSearchURL(searchQuery, choice);
        Log.d("MainActivity", "got search url: " + swSearch);
        new SWSearchTask().execute(swSearch);


    }
    public class SWSearchTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicatorPB.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String swSearch = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.doHTTPGet(swSearch);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            //mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
            if (s != null) {
                //mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                //mSearchResultsRV.setVisibility(View.VISIBLE);
                ArrayList<StarWarsUtils.SearchResult> searchResultsList = StarWarsUtils.parseStarWarsSearchResultsJSON(s);
                //adapter.updateSearchResults(searchResultsList);
            } else {
                //mSearchResultsRV.setVisibility(View.INVISIBLE);
                //mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
            }
        }

        public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.radioButton:
                    if (checked)
                        choice = "planets";
                    break;
                case R.id.radioButton2:
                    if (checked)
                        choice = "people";
                    break;
                case R.id.radioButton3:
                    if (checked)
                        choice = "vehicles";
                    break;
            }
        }
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_search:
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.nav_compare:
                mDrawerLayout.closeDrawers();
                Intent compare = new Intent(this, CompareSearch.class);
                startActivity(compare);
                return true;
            default:
                return false;
        }
    }
}
