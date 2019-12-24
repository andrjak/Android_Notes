package com.artisanter.noteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity  // Указание для Room
class Note // Класс для представления Заметок
{
    Note(){
        date = new Date();
    }
    public String content;
    public String title;
    public String tags;
    @TypeConverters({DateConverter.class}) // Конвертация типа для хранения в БД
    public Date date;

    @PrimaryKey(autoGenerate = true) // Авто генерация первичного ключа
    public long uid = 0;
}
