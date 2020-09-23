package com.example.architectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.architectureexample.EXTRA_PRIORITY";

    private EditText title, description;
    private NumberPicker priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.edit_text_title);
        description = findViewById(R.id.edit_text_description);
        priority = findViewById(R.id.npPriority);

        priority.setMaxValue(10);
        priority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = this.title.getText().toString().trim(),
        description = this.description.getText().toString().trim();
        int priority = this.priority.getValue();

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Insert a title and a description",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
                saveNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}