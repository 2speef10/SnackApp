package com.example.snackapp.Fragment;

import android.media.RouteListingPreference;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.snackapp.R;
import com.example.snackapp.Model.Bestelling;
import com.example.snackapp.Views.BestellingAdapter;
import com.example.snackapp.Views.BestellingViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerViewBestellingen;
    private BestellingAdapter bestellingAdapter;
    private SearchView searchView;
    private List<Bestelling> BestellingList;


    public OrderFragment() {
        // Lege openbare constructor vereist
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Gebruik de juiste layout voor het fragment
        return inflater.inflate(R.layout.order_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewBestellingen = view.findViewById(R.id.recyclerViewBestellingen);
        bestellingAdapter = new BestellingAdapter();

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewBestellingen.setAdapter(bestellingAdapter);
        recyclerViewBestellingen.setLayoutManager(layoutManager);
        BestellingList = new ArrayList<>();

        BestellingViewModel bestellingViewModel = new ViewModelProvider(getActivity()).get(BestellingViewModel.class);
        bestellingViewModel.getAllBestellingen().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<List<Bestelling>>() {
            @Override
            public void onChanged(List<Bestelling> bestellingen) {
                BestellingList = bestellingen; // Update BestellingList met de nieuwe gegevens
                bestellingAdapter.AddItems(bestellingen);
                bestellingAdapter.notifyDataSetChanged();
            }
        });
    }

    private void filterList(String text) {
        List<Bestelling> filteredList = new ArrayList<>();

        for (Bestelling bestelling : BestellingList) {
            // Vergelijk tekst met de lowercase versie van de saus van de bestelling
            if (bestelling.getSauce().toLowerCase().contains(text.toLowerCase())) {
                // Voeg alleen de bestelling toe aan de lijst
                filteredList.add(bestelling);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            // Update de adapter met de nieuwe lijst van bestellingen
            bestellingAdapter.setFilteredList(filteredList);
        }
    }


}

