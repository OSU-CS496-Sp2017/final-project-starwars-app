package com.example.cs496finalproject.finalprojectstarwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    private RecyclerView.Adapter rv_adapter;
    //private List<WeatherInfo> weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

    }
}
