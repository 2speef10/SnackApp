package com.example.snackapp.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snackapp.Model.Bestelling;
import com.example.snackapp.R;

import java.util.ArrayList;
import java.util.List;

public class BestellingAdapter extends RecyclerView.Adapter<BestellingAdapter.BestellingViewHolder> {

    private List<Bestelling> bestellingen;

    public BestellingAdapter() {
        this.bestellingen = new ArrayList<Bestelling>();
    }

    @NonNull
    @Override
    public BestellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Hier kun je direct de View van activity_main.xml inflaten
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new BestellingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BestellingViewHolder holder, int position) {
        Bestelling currentBestelling = bestellingen.get(position);

        // Hier kun je direct naar de Spinners in activity_main.xml verwijzen
        holder.toppingSpinner.setSelection(getIndex(holder.toppingSpinner, currentBestelling.getTopping()));
        holder.sizeSpinner.setSelection(getIndex(holder.sizeSpinner, currentBestelling.getSize()));
        // Voeg hier de andere velden toe

        // Je kunt hier ook klikgebeurtenissen toevoegen als dat nodig is
    }

    @Override
    public int getItemCount() {
        return bestellingen.size();
    }

    static class BestellingViewHolder extends RecyclerView.ViewHolder {
        private Spinner toppingSpinner;
        private Spinner sizeSpinner;
        // Voeg hier de andere Spinners toe voor de overige velden

        public BestellingViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hier kun je direct de Spinners in activity_main.xml vinden
            toppingSpinner = itemView.findViewById(R.id.spinnerToppings);
            sizeSpinner = itemView.findViewById(R.id.spinnerSizes);
            // Voeg hier de andere Spinners toe voor de overige velden
        }
    }

    // Hulpmethode om de index van een item in de Spinner te vinden
    private int getIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }
    public void AddItems(List<Bestelling>newBestellingen){
        this.bestellingen = new ArrayList<Bestelling>();
        this.bestellingen.addAll(newBestellingen);
    }
}

