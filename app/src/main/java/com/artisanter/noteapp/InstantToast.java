package com.artisanter.noteapp;

import android.content.Context;
import android.widget.Toast;

// Класс для вывода сообщений
public class InstantToast
{
    private static Toast previous;

    public static void showText(Context context, String text)
    {
        if(previous != null)
            previous.cancel();
        previous = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        previous.show();
    }
}
