package com.example.snackapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.snackapp.R;
import com.example.snackapp.Model.Bestelling;
import com.example.snackapp.Views.BestellingAdapter;
import com.example.snackapp.Views.BestellingViewModel;
import java.util.List;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerViewBestellingen;
    private BestellingAdapter bestellingAdapter;

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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewBestellingen.setAdapter(bestellingAdapter);
        recyclerViewBestellingen.setLayoutManager(layoutManager);

        BestellingViewModel bestellingViewModel = new ViewModelProvider(getActivity()).get(BestellingViewModel.class);
        bestellingViewModel.getAllBestellingen().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<List<Bestelling>>() {
            @Override
            public void onChanged(List<Bestelling> bestellingen) {
                bestellingAdapter.AddItems(bestellingen);
                bestellingAdapter.notifyDataSetChanged();
            }
        });
    }
}
