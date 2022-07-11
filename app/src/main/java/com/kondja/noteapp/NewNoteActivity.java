package com.kondja.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewNoteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.kondja.noteapp.REPLY";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        editText = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent intent = new Intent();
            if(TextUtils.isEmpty(editText.getText())){
                setResult(RESULT_CANCELED, intent);
            }else{
                String note = editText.getText().toString();
                intent.putExtra(EXTRA_REPLY, note);
                setResult(RESULT_OK,intent);
            }
            finish();
        });
    }
}