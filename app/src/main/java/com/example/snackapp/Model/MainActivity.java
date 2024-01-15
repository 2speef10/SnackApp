package com.example.snackapp.Model;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.snackapp.DAO.BestellingDao;
import com.example.snackapp.Fragment.OrderFragment;
import com.example.snackapp.R;
import com.example.snackapp.Views.BestellingViewModel;

public class MainActivity extends AppCompatActivity {
    Spinner toppings;
    Spinner size;
    Spinner Sauce;
    Spinner kruditeit;
    Spinner extras;
    Spinner dranks;
    Button afronden;
    Button AddToBag;
    ImageView ImageBurger;
    TextView Adress;
    TextView Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // Afronden-knop klikgebeurtenis
        afronden = findViewById(R.id.btnAfronden);
        AddToBag = findViewById(R.id.btnAddToBag);
        kruditeit = findViewById(R.id.spinnerHerbs);
        dranks = findViewById(R.id.spinnerDrinks);
        extras = findViewById(R.id.spinnerExtras);
        size = findViewById(R.id.spinnerSizes);
        Sauce = findViewById(R.id.spinnerSauce);
        toppings = findViewById(R.id.spinnerToppings);
        ImageBurger = findViewById(R.id.imagePizza);
        Adress = findViewById(R.id.tvAddress);
        Phone = findViewById(R.id.tvPhoneNumber);

        afronden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hier de onClickAfronden-methode aanroepen
                onClickAfronden();
            }
        });
        AddToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to add the order to the database
                onClickAddToBag();
            }
        });

    }

    public void onClickAfronden() {
        // Laad het order_layout
        FragmentManager fragmentManager = getSupportFragmentManager();

        toppings.setVisibility(View.GONE);
        dranks.setVisibility(View.GONE);
        Sauce.setVisibility(View.GONE);
        kruditeit.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        extras.setVisibility(View.GONE);
        Adress.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        AddToBag.setVisibility(View.GONE);
        afronden.setVisibility(View.GONE);

        // Toon de bestellingen in het OrderFragment
        OrderFragment orderFragment = new OrderFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coordinator, orderFragment).commit();
    }
    private void onClickAddToBag() {
        // Haal geselecteerde opties op
        String selectedTopping = toppings.getSelectedItem().toString();
        String selectedSize = size.getSelectedItem().toString();
        String selectedSauce = Sauce.getSelectedItem().toString();
        String selectedHerbs = kruditeit.getSelectedItem().toString();
        String selectedExtras = extras.getSelectedItem().toString();
        String selectedDrinks = dranks.getSelectedItem().toString();

        // Maak een Bestelling object aan met de geselecteerde opties
        Bestelling bestelling = new Bestelling(selectedTopping, selectedSize, selectedSauce, selectedHerbs, selectedExtras, selectedDrinks);

        // Roep een methode aan om de bestelling toe te voegen aan de database
        voegBestellingToeAanDatabase(bestelling);
    }
    private void voegBestellingToeAanDatabase(Bestelling bestelling) {
        // Get the BestellingDao from your Room database instance
        BestellingDao bestellingDao = DatabaseHelper.getInstance(this).getBestellingDao();

        // Insert the order into the database using Executor for background thread
        BestellingViewModel bestellingViewModel = new ViewModelProvider(this).get(BestellingViewModel.class);
        bestellingViewModel.insert(bestelling);

        // Optionally, you can show a message or perform additional actions after adding to the database
        Toast.makeText(this, "Bestelling toegevoegd ", Toast.LENGTH_SHORT).show();
    }

}
