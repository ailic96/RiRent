package com.eggdoggo.rirent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddRentActivity extends AppCompatActivity {


    Button addButton;
    ActionBar actionBar;
    private EditText pRentEt, pRistanEt, pElectricityEt, pInternetEt, pStairsEt;
    private Spinner pDateEt;

    private String rent, ristan, electricity, internet, stairs, date, timeStamp;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent);

        Spinner dropdown = findViewById(R.id.date);
        //create a list of items for the spinner.
        String[] items = new String[]{"January","February","March","April",
                "May","June","July","August","September","October","November","December"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Novi unos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pRentEt = findViewById(R.id.rent);
        pRistanEt = findViewById(R.id.ristan);
        pElectricityEt = findViewById(R.id.electricity);
        pInternetEt = findViewById(R.id.internet);
        pStairsEt = findViewById(R.id.stairs);
        pDateEt = findViewById(R.id.date);


        addButton = findViewById(R.id.addButton);

        //Initiate database object in main function
        dbHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData() {

        rent = ""+pRentEt.getText().toString().trim();
        ristan = ""+pRistanEt.getText().toString().trim();
        electricity = ""+pElectricityEt.getText().toString().trim();
        internet = ""+pInternetEt.getText().toString().trim();
        stairs = ""+pStairsEt.getText().toString().trim();

        date = ""+pDateEt.getSelectedItem().toString().trim();
        timeStamp = ""+System.currentTimeMillis();

        long id = dbHelper.insertInfo(
                ""+rent,
                ""+ristan,
                ""+electricity,
                ""+internet,
                ""+stairs,
                ""+date,
                ""+timeStamp,
                ""+timeStamp
        );

        //Toast.makeText(this, "Record added to id: "+id, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddRentActivity.this, MainActivity.class));
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
