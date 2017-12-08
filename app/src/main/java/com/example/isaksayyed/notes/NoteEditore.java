package com.example.isaksayyed.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditore extends AppCompatActivity {
     int noteid;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editore);
        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
      noteid = intent.getIntExtra("Notes",-1);
      if (noteid != -1) {
          editText.setText(MainActivity.Notes.get(noteid));
      }
      else {
          MainActivity.Notes.add("");
           noteid =MainActivity.Notes.size()-1;
          MainActivity.arrayAdapter.notifyDataSetChanged();

      }
editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      MainActivity.Notes.set(noteid,String.valueOf(charSequence));
      MainActivity.arrayAdapter.notifyDataSetChanged();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.isaksayyed.notes", Context.MODE_PRIVATE);
        HashSet<String>set = new HashSet<>(MainActivity.Notes);
        sharedPreferences.edit().putStringSet("Notes",set).apply();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});

    }
}
