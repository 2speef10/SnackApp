package com.example.snackapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.snackapp.Model.User;


@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);



    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(String email, String password);



    // Andere benodigde query's voor gebruikersbeheer toevoegen
}
