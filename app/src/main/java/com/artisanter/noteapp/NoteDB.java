package com.artisanter.noteapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Это лишь описание структуры базы данных, но сама база данных будет находится в одном файле.
@Database(entities = {Note.class}, version = 3, exportSchema = false)
public abstract class NoteDB extends RoomDatabase // Класс базы данных
{
    public abstract NoteDao noteDao();
}