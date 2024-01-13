package com.example.snackapp.Views;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.snackapp.DAO.BestellingDao;
import com.example.snackapp.Model.DatabaseHelper;
import com.example.snackapp.Model.Bestelling;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BestellingViewModel extends AndroidViewModel {
    private BestellingDao bestellingDao;
    private Executor executor;


    public BestellingViewModel(@NonNull Application application) {
        super(application);
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        bestellingDao = database.getBestellingDao();
        executor = Executors.newFixedThreadPool(2);

    }
    public void insert(Bestelling bestelling) {
        executor.execute(() -> bestellingDao.insert(bestelling));
    }

    public void delete(Bestelling bestelling) {
        executor.execute(() -> bestellingDao.delete(bestelling));
    }

    public void update(Bestelling bestelling) {
        executor.execute(() -> bestellingDao.update(bestelling));
    }

    public Bestelling getBestellingById(int bestellingId) {
        // Deze methode kan synchroon worden aangeroepen, aangezien Room asynchrone queries op de achtergrond afhandelt
        return bestellingDao.getBestellingById(bestellingId);
    }

    public void deleteAllBestellingen() {
        executor.execute(() -> bestellingDao.deleteAllBestellingen());
    }
    public LiveData<List<Bestelling> >getAllBestellingen(){
        return bestellingDao.getAllBestellingen();
    }

}

