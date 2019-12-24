package com.artisanter.noteapp;

import android.app.Application;
import android.content.Intent;

import androidx.room.Room;

public class App extends Application // Базовый класс для поддержания глобального состояния приложения
{
    public static App instance; // Текущий экземпляр

    private NoteDB database; // Подключение к базе данных

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, NoteDB.class, "database")
                .allowMainThreadQueries()
                .build(); // Получение базы данных

        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public static App getInstance() // Получение экземпляра
    {
        return instance;
    }

    public NoteDB getDatabase()  // Получение базы данных
    {
        return database;
    }

    public NoteDao getDao()  // Получение интерфейса базы данных
    {
        return database.noteDao();
    }
}