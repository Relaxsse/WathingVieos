package com.example.wathingvieos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private static final int MAX_MESSAGE_LENGTH = 100;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ArrayList<String> comentaris = new ArrayList<>();

    RecyclerView recyclerView;
    Button btn;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent in = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editText = (EditText) findViewById(R.id.editTextMessage);
        btn = (Button) findViewById(R.id.buttonSend);
        String beseda = in.getStringExtra(MainActivity2.EXTRA_MESSAGER);
        DatabaseReference myRef = database.getReference(beseda);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter dataAdapter = new DataAdapter(this, comentaris);
        recyclerView.setAdapter(dataAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kom = editText.getText().toString();
                if (kom.equals("")) {
                    Toast.makeText(getApplicationContext(), "Введите сообщение!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (kom.length() > MAX_MESSAGE_LENGTH) {
                    Toast.makeText(getApplicationContext(), "Слишком длинное сообщение!", Toast.LENGTH_LONG).show();
                    return;
                }
                myRef.push().setValue(kom);
                editText.setText("");
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String kom = snapshot.getValue(String.class);
                comentaris.add(kom);
                dataAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(comentaris.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}