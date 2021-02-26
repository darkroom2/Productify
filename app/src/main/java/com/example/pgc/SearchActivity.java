package com.example.pgc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchItem(View view) {
        EditText name, producer, city;
        name = findViewById(R.id.editTextProductName);
        producer = findViewById(R.id.editTextProducerName);
        city = findViewById(R.id.editTextCityName);

        ArrayList<String> list = new ArrayList<>();
        list.add(name.getText().toString());
        list.add(producer.getText().toString());
        list.add(city.getText().toString());

        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putStringArrayListExtra("queryParams", list);
        startActivity(intent);
    }
}