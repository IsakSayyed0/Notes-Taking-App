package com.example.isaksayyed.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
   static ArrayAdapter<String>arrayAdapter;
          static   ArrayList<String>Notes = new ArrayList<>() ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addnote){
            Intent intent = new   Intent(getApplicationContext(),NoteEditore.class);


            startActivity(intent);
return  true;
        }
        return  false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.Notes );
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.isaksayyed.notes", Context.MODE_PRIVATE);
           HashSet<String>set =(HashSet<String>) sharedPreferences.getStringSet("Notes",null);
           if (set == null){
               Notes.add("add your note");

           }
else {
               Notes = new ArrayList<>(set);
           }


        arrayAdapter  = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,Notes);
  listView.setAdapter(arrayAdapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent =new Intent(getApplicationContext(),NoteEditore.class);

        intent.putExtra("Notes",i);
     startActivity(intent);
    }
});
listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

      final int itemtod = i;
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.editbox_dropdown_light_frame)
                .setTitle("Delete !!")
                .setMessage("Are you sure you want to delete ? ")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Notes.remove(itemtod);
                        arrayAdapter.notifyDataSetChanged();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.isaksayyed.notes", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet<>(MainActivity.Notes);
                        sharedPreferences.edit().putStringSet("Notes",set).apply();

                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();

                                }
                }).setNegativeButton("No", null )

        .show();

        return true;
    }
});

    }
}
