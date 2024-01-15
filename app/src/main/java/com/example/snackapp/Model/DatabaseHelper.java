package com.example.snackapp.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.snackapp.DAO.BestellingDao;
import com.example.snackapp.DAO.UserDao;

@Database(version = 1, entities = {User.class, Bestelling.class})
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String TAG = "DatabaseHelper";
    private static DatabaseHelper INSTANCE;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, "SnackApp.sqlite")
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();
    public abstract BestellingDao getBestellingDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d(TAG, "Database geopend");
            // Zorg ervoor dat de database niet voortijdig wordt gesloten tijdens initialisatie
            if (!db.isOpen()) {
                Log.e(TAG, "Database is voortijdig gesloten tijdens initialisatie");
                // Handel dit geval op de juiste manier af, gooi een uitzondering of neem corrigerende maatregelen
            }
        }

        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "Database aangemaakt");

            // Initialiseer tabellen of voer initiële gegevensinvoer uit indien nodig
            // Voorbeeld: db.execSQL("CREATE TABLE IF NOT EXISTS ...");

            // Geen noodzaak voor getWritableDatabase(); je hebt hier al toegang tot db
        }
    };
}


