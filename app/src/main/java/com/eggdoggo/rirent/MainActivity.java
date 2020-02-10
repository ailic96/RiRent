package com.eggdoggo.rirent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    //ImageButton fab;
    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Svi podaci");

        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddRentActivity.class));
            }
        });
    }

    private void showRecord(){
        Adapter adapter = new Adapter(MainActivity.this, databaseHelper.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        showRecord();
    }
}
