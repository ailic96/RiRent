package com.eggdoggo.rirent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
* MainActivity displays recyclerview adapter and shows values from the database.
* It also has control of a butten used for adding new values.
* */

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    @Nullable
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    @Nullable
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action bar control
        actionBar = getSupportActionBar();
        actionBar.setTitle("Svi podaci");

        //fetch recyclerview and databaseHelper constructor
        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        /* Add button functionality */

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRentActivity.class);
                intent.putExtra("editMode", false);
                startActivity(intent);
            }
        });
    }

    /* Show values from database through adapter */

    private void showRecord(){
        Adapter adapter = new Adapter(MainActivity.this, databaseHelper.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    /* Resume activity lifecycle */

    protected void onResume() {
        super.onResume();
        showRecord();
    }

    /*Back key in actionbar*/

    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
