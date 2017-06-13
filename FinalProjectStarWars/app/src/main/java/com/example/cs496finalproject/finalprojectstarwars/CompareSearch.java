package com.example.cs496finalproject.finalprojectstarwars;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Godtop on 6/6/2017.
 */

public class CompareSearch extends AppCompatActivity implements StarWarsAdapter.OnSearchResultClickListener {

    private RecyclerView mCompareSearchResultsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_results);

        StarWarsAdapter adapter = new StarWarsAdapter(this);
       // mCompareSearchResultsRV = (RecyclerView)findViewById(R.id.rv_saved_search_results);
        mCompareSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mCompareSearchResultsRV.setHasFixedSize(true);
        mCompareSearchResultsRV.setAdapter(adapter);

    }

}
