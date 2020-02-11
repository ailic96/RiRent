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
import android.widget.TextView;
import android.widget.Toast;


public class AddRentActivity extends AppCompatActivity {

    Button addButton;
    ActionBar actionBar;

    private EditText pRentEt, pRistanEt, pElectricityEt, pInternetEt, pStairsEt;
    private Spinner pDateEt, pPersonEt;
    private float totalF, totaleF, totalpF;
    private String rent, ristan, electricity, internet, stairs, total, total_expenses, total_person, numPeople, date, timeStamp;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent);

        Spinner dropdown = findViewById(R.id.date);
        //create a list of items for the spinner.
        String[] items = new String[]{"Siječanj","Veljača","Ožujak","Travanj",
                "Svibanj","Lipanj","Srpanj","Kolovoz","Rujan","Listopad","Studeni","Prosinac"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Spinner dropdownPeople = findViewById(R.id.peopleNum);
        //create a list of items for the spinner.
        String[] itemsPeople = new String[]{"1", "2", "3", "4", "5", "6"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterPeople = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsPeople);
        //set the spinners adapter to the previously created one.
        dropdownPeople.setAdapter(adapterPeople);

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
        pPersonEt = findViewById(R.id.peopleNum);

        addButton = findViewById(R.id.addButton);

        //Initiate database object in main function
        dbHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(AddRentActivity.this, MainActivity.class));
                Toast.makeText(AddRentActivity.this, "Unos uspješan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {

        rent = ""+pRentEt.getText().toString().trim();
        ristan = ""+pRistanEt.getText().toString().trim();
        electricity = ""+pElectricityEt.getText().toString().trim();
        internet = ""+pInternetEt.getText().toString().trim();
        stairs = ""+pStairsEt.getText().toString().trim();

        numPeople = ""+pPersonEt.getSelectedItem().toString().trim();
        date = ""+pDateEt.getSelectedItem().toString().trim();

        timeStamp = ""+System.currentTimeMillis();

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

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
