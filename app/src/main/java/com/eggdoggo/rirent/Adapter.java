package com.eggdoggo.rirent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Model model = arrayList.get(position);
        //get view
        final String id = model.getId();
        final String rent = model.getRent();
        final String ristan = model.getRistan();
        final String electricity = model.getElectricity();
        final String internet = model.getInternet();
        final String stairs = model.getStairs();
        final String total = model.getTotal();
        final String total_expenses = model.getTotalExpenses();
        final String total_person = model.getTotalPerson();
        final String num_people = model.getNumPeople();
        final String date = model.getDate();
        final String addTimeStamp = model.getAddTimeStamp();
        final String updateTimeStamp = model.getUpdateTimeStamp();

        //Set views
        holder.rent.setText(rent);
        holder.ristan.setText(ristan);
        holder.electricity.setText(electricity);
        holder.internet.setText(internet);
        holder.stairs.setText(stairs);
        holder.total.setText(total);
        holder.total_expenses.setText(total_expenses);
        holder.total_person.setText(total_person);
        //holder.num_people.setText(num_people);
        holder.date.setText(date);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+rent,
                        ""+ristan,
                        ""+electricity,
                        ""+internet,
                        ""+stairs,
                        ""+total,
                        ""+total_expenses,
                        ""+total_person,
                        ""+num_people,
                        ""+date,
                        ""+addTimeStamp,
                        ""+updateTimeStamp
                );
            }
        });
    }

    private void editDialog(String position, final String id, final String rent, final String ristan,
                            final String electricity, final String internet, final String stairs,final String total, final String total_expenses, final String total_person,
                            final String num_people, final String date, final String addTimeStamp, final String updateTimeStamp) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Ažuriranje podataka");
        builder.setMessage("Želite li izmjeniti podatke?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_edit);

        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, EditRentActivity.class );
                intent.putExtra("ID", id);
                intent.putExtra("RENT", rent);
                intent.putExtra("RISTAN", ristan);
                intent.putExtra("ELECTRICITY", electricity);
                intent.putExtra("INTERNET", internet);
                intent.putExtra("STAIRS", stairs);
                intent.putExtra("TOTAL", total);
                intent.putExtra("TOTAL_EXPENSES", total);
                intent.putExtra("TOTAL_PERSON", total_person);
                intent.putExtra("NUM_PEOPLE", num_people);
                intent.putExtra("DATE", date);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
                intent.putExtra("editMode", true);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView rent, ristan, electricity, internet, stairs, total, total_expenses, total_person, date;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            rent = itemView.findViewById(R.id.rent);
            ristan = itemView.findViewById(R.id.ristan);
            electricity = itemView.findViewById(R.id.electricity);
            internet = itemView.findViewById(R.id.internet);
            stairs = itemView.findViewById(R.id.stairs);
            total = itemView.findViewById(R.id.total);
            total_expenses = itemView.findViewById(R.id.total_expenses);
            total_person = itemView.findViewById(R.id.total_person);
            //num_people = itemView.findViewById(R.id.peopleNum);
            date = itemView.findViewById(R.id.date);

            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}

