package com.example.snackapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.snackapp.Model.Bestelling;

import java.util.List;

@Dao
public interface BestellingDao {
    @Insert
    void insert(Bestelling bestelling);

    @Delete
    void delete(Bestelling bestelling);

    @Update
    void update(Bestelling bestelling);

    @Query("SELECT * FROM bestelling_table")
    LiveData<List<Bestelling>> getAllBestellingen();

    @Query("SELECT * FROM bestelling_table WHERE id = :bestellingId")
    Bestelling getBestellingById(int bestellingId);

    @Query("DELETE FROM bestelling_table")
    void deleteAllBestellingen();

    @Query("SELECT * FROM bestelling_table WHERE sauce LIKE :sauce")
    LiveData<List <Bestelling>> getBestellingBySauce (String sauce);
}

