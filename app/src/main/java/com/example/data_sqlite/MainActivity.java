    package com.example.data_sqlite;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class MainActivity extends AppCompatActivity {

    EditText edtNote;
    Button btnSave;
    RecyclerView rvNotes;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNote = findViewById(R.id.edtNote);
        btnSave = findViewById(R.id.btnSave);
        rvNotes = findViewById(R.id.rvNotes);

        db = new DatabaseHandler(this);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        loadData();

        btnSave.setOnClickListener(v -> {
            String text = edtNote.getText().toString().trim();
            if (!text.isEmpty()) {
                db.addNote(text);
                edtNote.setText("");
                loadData();
            }
        });
    }

    private void loadData() {
        NoteAdapter adapter = new NoteAdapter(db.getAllNotes());
        rvNotes.setAdapter(adapter);
    }
}
