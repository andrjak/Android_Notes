package com.artisanter.noteapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;

// Активити для добавления и изменения заметок
public class NoteActivity extends AppCompatActivity
{
    Bundle bundle;
    TextView title;
    TextView content;
    TextView tags;
    Note note = new Note();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        bundle = getIntent().getExtras(); // Получаем заметку в случае если мы её изменяем
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        tags = findViewById(R.id.tags);

        if(bundle == null)
        {
            note = new Note();
            title.setHint(dateFormat.format(note.date));
            return;
        }
        note = App.getInstance().getDao().getById(bundle.getLong("uid"));
        title.setText(note.title);
        content.setText(note.content);
        tags.setText(note.tags);
    }

    public void saveClick(View view) // Нажатие на кнопку сохранения
    {
        note.content = content.getText().toString();
        if(note.content.isEmpty()) // Если содержимое пустое
        {
            InstantToast.showText(getApplicationContext(), "Заметка пуста");
            return;
        }
        note.title = title.getText().toString();
        if(note.title.isEmpty()) // Проверка является ли заголовок пустым
        {
            note.title = dateFormat.format(note.date);
        }
        note.tags = tags.getText().toString();
        if(bundle == null) // Если заметка до этого не существовала
        {
            App.getInstance().getDao().insert(note);
            InstantToast.showText(getApplicationContext(), "Заметка добавлена");
        }
        else // Если заметка изменялась
        {
            App.getInstance().getDao().update(note);
            InstantToast.showText(getApplicationContext(), "Заметка сохранена");
        }
        finish(); // Встроенный метод в App посути обновление состояния
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        backDialog();
    }

    private void backDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        quitDialog.setTitle("Данные могут быть утеряны: Вы уверены?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }
}
