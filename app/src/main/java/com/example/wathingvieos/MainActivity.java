package com.example.wathingvieos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.wathingvieos.message";

    Intent i;
    String[] vibor = {"Первое видео", "Второе видео", "Третье видео", "Четвертое видео"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vibor);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int respect = position;
                String itemid = Integer.toString(respect);
                i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra(EXTRA_MESSAGE, itemid);
                startActivity(i);
            }
        });
    }
}