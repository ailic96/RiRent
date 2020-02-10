package com.eggdoggo.rirent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Model model = arrayList.get(position);
        //get for view
        String id = model.getId();
        String rent = model.getRent();
        String ristan = model.getRistan();
        String electricity = model.getElectricity();
        String internet = model.getInternet();
        String stairs = model.getStairs();
        String date = model.getDate();

        holder.rent.setText(rent);
        holder.ristan.setText(ristan);
        holder.electricity.setText(electricity);
        holder.internet.setText(internet);
        holder.stairs.setText(stairs);
        holder.date.setText(date);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView rent, ristan, electricity, internet, stairs, date;

        public Holder(@NonNull View itemView) {
            super(itemView);

            rent = itemView.findViewById(R.id.rent);
            ristan = itemView.findViewById(R.id.ristan);
            electricity = itemView.findViewById(R.id.electricity);
            internet = itemView.findViewById(R.id.internet);
            stairs = itemView.findViewById(R.id.stairs);
            date = itemView.findViewById(R.id.date);
        }
    }
}

