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

    public void setFilteredList(List<Bestelling>filteredList){
        this.bestellingen = filteredList;
        notifyDataSetChanged();

    }

    public BestellingAdapter() {
        this.bestellingen = new ArrayList<>();
    }

    @NonNull
    @Override
    public BestellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Hier moet je de View van bestelling_item.xml inflaten
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bestelling_item, parent, false);
        return new BestellingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BestellingViewHolder holder, int position) {
        Bestelling currentBestelling = bestellingen.get(position);

        // Hier kun je de gegevens van de huidige bestelling instellen op de ViewHolder
        holder.bind(currentBestelling);
    }

    @Override
    public int getItemCount() {
        return bestellingen.size();
    }

    static class BestellingViewHolder extends RecyclerView.ViewHolder {
        private TextView toppingTextView;
        private TextView sizeTextView;
        private TextView sauceTextView; // Voeg de andere TextViews toe voor de overige velden
        private TextView spiceLevelTextView;
        private TextView extrasTextView;
        private TextView drankTextView;


        public BestellingViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hier kun je de TextViews in bestelling_item.xml vinden
            toppingTextView = itemView.findViewById(R.id.textViewToppingValue);
            sizeTextView = itemView.findViewById(R.id.textViewSizeValue);
            sauceTextView = itemView.findViewById(R.id.textViewSauceValue); // Voeg de andere TextViews toe
            spiceLevelTextView = itemView.findViewById(R.id.textViewSpiceLevelValue);
            extrasTextView = itemView.findViewById(R.id.textViewExtrasValue);
            drankTextView = itemView.findViewById(R.id.textViewDrankValue);
        }

        // Deze methode bindt de gegevens van de huidige bestelling aan de Views
        public void bind(Bestelling bestelling) {
            toppingTextView.setText(bestelling.getTopping());
            sizeTextView.setText(bestelling.getSize());
            sauceTextView.setText(bestelling.getSauce()); // Zorg ervoor dat de juiste getter wordt gebruikt
            spiceLevelTextView.setText(bestelling.getHerbs());
            extrasTextView.setText(bestelling.getExtras());
            drankTextView.setText(bestelling.getDrinks());
        }

    }


    public void AddItems(List<Bestelling> newBestellingen) {
        this.bestellingen.addAll(newBestellingen);
        notifyDataSetChanged();
    }

}



