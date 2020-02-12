package com.eggdoggo.rirent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        actionBar = getSupportActionBar();
        actionBar.setTitle("Svi podaci");

        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

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

    private void showRecord(){
        Adapter adapter = new Adapter(MainActivity.this, databaseHelper.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        showRecord();
    }

    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
