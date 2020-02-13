package com.eggdoggo.rirent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/* EditRentActivity class is accessed by clicking edit button in Recyclerview. Difference between
* EditRentActivity and AddRentActivity is that it loads previously added values and offers update
* option.
*/

public class EditRentActivity extends AppCompatActivity {


    @Nullable
    ActionBar actionBar;
    Button addButton;

    private EditText pRentEt, pRistanEt, pElectricityEt, pInternetEt, pStairsEt;
    private Spinner pDateEt, pPersonEt;
    private float totalF, totaleF, totalpF;
    @Nullable
    private String id, rent, ristan, electricity, internet, stairs, total, total_expenses, total_person, peopleNum, date, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    @Nullable
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rent);

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

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode",editMode);

        /* Forwards previously assigned values */

        id = intent.getStringExtra("ID");
        rent = intent.getStringExtra("RENT");
        ristan = intent.getStringExtra("RISTAN");
        electricity = intent.getStringExtra("ELECTRICITY");
        internet = intent.getStringExtra("INTERNET");
        stairs = intent.getStringExtra("STAIRS");
        total = intent.getStringExtra("TOTAL");
        total_expenses = intent.getStringExtra("TOTAL_EXPENSES");
        total_person = intent.getStringExtra("TOTAL_PERSON");
        peopleNum = intent.getStringExtra("NUM_PEOPLE");
        date = intent.getStringExtra("DATE");
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if(editMode){
            actionBar.setTitle("Ažuriranje podataka");

            editMode = intent.getBooleanExtra("editMode",editMode);
            id = intent.getStringExtra("ID");
            rent = intent.getStringExtra("RENT");
            ristan = intent.getStringExtra("RISTAN");
            electricity = intent.getStringExtra("ELECTRICITY");
            internet = intent.getStringExtra("INTERNET");
            stairs = intent.getStringExtra("STAIRS");
            total = intent.getStringExtra("TOTAL");
            total_expenses = intent.getStringExtra("TOTAL_EXPENSES");
            total_person = intent.getStringExtra("TOTAL_PERSON");
            peopleNum = intent.getStringExtra("NUM_PEOPLE");
            date = intent.getStringExtra("DATE");
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            pRentEt.setText(rent);
            pRistanEt.setText(ristan);
            pElectricityEt.setText(electricity);
            pInternetEt.setText(internet);
            pStairsEt.setText(stairs);

            setSpinText(pDateEt, date);
            setSpinText(pPersonEt, peopleNum);
        }
        else{
            actionBar.setTitle("Dodavanje podataka: ");
        }

        //Initiate database object in main function
        dbHelper = new DatabaseHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        /* Enables value updating button functionality */
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(EditRentActivity.this, MainActivity.class));
                Toast.makeText(EditRentActivity.this, "Ažuriranje uspješno!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Get and set values for updating */

    private void getData() {

        rent = ""+pRentEt.getText().toString().trim();
        ristan = ""+pRistanEt.getText().toString().trim();
        electricity = ""+pElectricityEt.getText().toString().trim();
        internet = ""+pInternetEt.getText().toString().trim();
        stairs = ""+pStairsEt.getText().toString().trim();
        peopleNum = ""+pPersonEt.getSelectedItem().toString().trim();

        if(editMode){

            rent = dbHelper.checkIfNull(rent);
            ristan = dbHelper.checkIfNull(ristan);
            electricity = dbHelper.checkIfNull(electricity);
            internet = dbHelper.checkIfNull(internet);
            stairs = dbHelper.checkIfNull(stairs);


            date = ""+pDateEt.getSelectedItem().toString().trim();
            String timeStamp = ""+System.currentTimeMillis();

            //Total expense calculation (converted to type Float)
            totalF = Float.valueOf(rent)+Float.valueOf(ristan)+Float.valueOf(electricity)+
                    Float.valueOf(internet)+Float.valueOf(stairs);

            //Expenses calculation (converted to type Float)
            totaleF =Float.valueOf(ristan)+Float.valueOf(electricity)+
                    Float.valueOf(internet)+Float.valueOf(stairs);

            //Expenses per person (converted to type float
            totalpF = (Float.valueOf(rent)+Float.valueOf(ristan)+Float.valueOf(electricity)+
                    Float.valueOf(internet)+Float.valueOf(stairs))/Float.valueOf(peopleNum);

            //Expenses converted to String
            total = Float.toString(totalF);
            total_expenses = Float.toString(totaleF);
            total_person = Float.toString(totalpF);

            //Gets current time
            String newUpdateTime = ""+System.currentTimeMillis();

            //Updates values calculated above by using a method defined in DatabaseHelper
            dbHelper.updateInfo(
                    ""+id,
                    ""+rent,
                    ""+ristan,
                    ""+electricity,
                    ""+internet,
                    ""+stairs,
                    ""+total,
                    ""+total_expenses,
                    ""+total_person,
                    ""+peopleNum,
                    ""+date,
                    ""+addTimeStamp,
                    ""+newUpdateTime
            );
        }
        else{
            String timeStamp = "" + System.currentTimeMillis();

            dbHelper.insertInfo(
                    ""+rent,
                    ""+ristan,
                    ""+electricity,
                    ""+internet,
                    ""+stairs,
                    ""+total,
                    ""+total_expenses,
                    ""+total_person,
                    ""+peopleNum,
                    ""+date,
                    ""+timeStamp,
                    ""+timeStamp
            );
        }
    }

    /*Back button functionality*/

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    /* Finds drop down item previously assigned in the database */

    public void setSpinText(@NonNull Spinner spin, @NonNull String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }
    }
}
