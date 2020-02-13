package com.eggdoggo.rirent;

import androidx.annotation.Nullable;
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

/*
* AddRentActivity enables input and calculation of values. It's directly associated with
* res/layout/activity_add_rent.xml.
*/

public class AddRentActivity extends AppCompatActivity {


    @Nullable
    ActionBar actionBar;
    Button addButton;
    private EditText pRentEt, pRistanEt, pElectricityEt, pInternetEt, pStairsEt;
    private Spinner pDateEt, pPersonEt;
    private float totalF, totaleF, totalpF;
    private String rent, ristan, electricity, internet, stairs, total, total_expenses, total_person, numPeople, date, timeStamp;

    @Nullable
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent);

        /*Spinner enables date choosing in a drop list*/

        Spinner dropdown = findViewById(R.id.date);
        //create a list of items for the spinner.
        String[] items = new String[]{"Siječanj","Veljača","Ožujak","Travanj",
                "Svibanj","Lipanj","Srpanj","Kolovoz","Rujan","Listopad","Studeni","Prosinac"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        /*Spinner enables number of people who pay the rent in a drop list*/

        Spinner dropdownPeople = findViewById(R.id.peopleNum);
        String[] itemsPeople = new String[]{"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapterPeople = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsPeople);
        dropdownPeople.setAdapter(adapterPeople);


        /* Action bar control */

        actionBar = getSupportActionBar();
        actionBar.setTitle("Novi unos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        /* Get values from numeric textfields in xml */

        pRentEt = findViewById(R.id.rent);
        pRistanEt = findViewById(R.id.ristan);
        pElectricityEt = findViewById(R.id.electricity);
        pInternetEt = findViewById(R.id.internet);
        pStairsEt = findViewById(R.id.stairs);

        pDateEt = findViewById(R.id.date);
        pPersonEt = findViewById(R.id.peopleNum);

        addButton = findViewById(R.id.addButton);

        /*Initiate database object in main function*/
        dbHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);


        /* Enables value adding button functionality */
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(AddRentActivity.this, MainActivity.class));
                Toast.makeText(AddRentActivity.this, "Unos uspješan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Gets data from XML, calculates values and prepares data for database storage */

    private void getData() {

        rent = ""+pRentEt.getText().toString().trim();
        ristan = ""+pRistanEt.getText().toString().trim();
        electricity = ""+pElectricityEt.getText().toString().trim();
        internet = ""+pInternetEt.getText().toString().trim();
        stairs = ""+pStairsEt.getText().toString().trim();

        numPeople = ""+pPersonEt.getSelectedItem().toString().trim();
        date = ""+pDateEt.getSelectedItem().toString().trim();

        timeStamp = ""+System.currentTimeMillis();

        //Checks if no value is inserted, puts "0" by default
        rent = dbHelper.checkIfNull(rent);
        ristan = dbHelper.checkIfNull(ristan);
        electricity = dbHelper.checkIfNull(electricity);
        internet = dbHelper.checkIfNull(internet);
        stairs = dbHelper.checkIfNull(stairs);

        //Total expense calculation (converted to type Float)
        totalF = Float.valueOf(rent)+Float.valueOf(ristan)+Float.valueOf(electricity)+
                Float.valueOf(internet)+Float.valueOf(stairs);

        //Expenses calculation (converted to type Float)
        totaleF =Float.valueOf(ristan)+Float.valueOf(electricity)+
                Float.valueOf(internet)+Float.valueOf(stairs);

        //Expenses per person (converted to type float)
        totalpF = (Float.valueOf(rent)+Float.valueOf(ristan)+Float.valueOf(electricity)+
                Float.valueOf(internet)+Float.valueOf(stairs))/Float.valueOf(numPeople);

        //Expenses converted to String
        total = Float.toString(totalF);
        total_expenses = Float.toString(totaleF);
        total_person = Float.toString(totalpF);

        //Inserts values calculated above by using a method defined in DatabaseHelper
        dbHelper.insertInfo(
                ""+rent,
                ""+ristan,
                ""+electricity,
                ""+internet,
                ""+stairs,
                ""+total,
                ""+total_expenses,
                ""+total_person,
                ""+numPeople,
                ""+date,
                ""+timeStamp,
                ""+timeStamp
        );
    }

    /*Back button functionality*/

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
