package com.example.snackapp.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.snackapp.DAO.UserDao;

@Database(version = 1, entities = {User.class})
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String TAG = "DatabaseHelper";
    private static DatabaseHelper INSTANCE;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, "VilloApp.sqlite")
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d(TAG, "Database opened");

            // Ensure that the database is not closed prematurely during initialization
            if (!INSTANCE.isOpen()) {
                Log.e(TAG, "Database closed prematurely during initialization");
                // You might want to handle this case appropriately
            }
        }

        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "Database created");
            // No need for getWritableDatabase(); you already have access to db here
        }
    };
}
