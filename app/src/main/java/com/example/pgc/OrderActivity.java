package com.example.pgc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pgc.database.AppDatabase;
import com.example.pgc.database.Order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderActivity extends AppCompatActivity {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private AppDatabase db;

    private int storeId;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        db = AppDatabase.getInstance(getApplicationContext());

        storeId = getIntent().getIntExtra("storeId", -1);
        productId = getIntent().getIntExtra("productId", -1);

        TextView storeNameTextView = findViewById(R.id.textStoreName2);
        TextView productNameTextView = findViewById(R.id.textProductName2);

        String storeName = getIntent().getStringExtra("storeName");
        String productName = getIntent().getStringExtra("productName");

        storeNameTextView.setText(storeName);
        productNameTextView.setText(productName);

    }

    public void makeOrder(View view) {
        executorService.execute(() -> {
            TextView firstNameTextView = findViewById(R.id.editTextFirstName);
            TextView lastNameTextView = findViewById(R.id.editTextLastName);
            TextView orderQtyTextView = findViewById(R.id.editTextOrderQty);
            TextView pickupDateTextView = findViewById(R.id.editTextPickupDate);

            String firstName = firstNameTextView.getText().toString();
            String lastName = lastNameTextView.getText().toString();
            int qty = Integer.parseInt(orderQtyTextView.getText().toString());
            String date = pickupDateTextView.getText().toString();

            SharedPreferences settings = getSharedPreferences("session", Context.MODE_PRIVATE);
            int result = settings.getInt("userId", -1);

            // Order(String userId, String productId, String storeId, String imie, String nazwisko, String data_odbioru, int ilosc, String status)
            Order temp = new Order(String.valueOf(result), String.valueOf(productId), String.valueOf(storeId), firstName, lastName, date, qty, "Złożona");
            db.orderDAO().insert(temp);

            runOnUiThread(() -> Toast.makeText(OrderActivity.this, "Rezerwacja przebiegła pomyślnie!", Toast.LENGTH_LONG).show());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // TODO: recursively finish() activities.. (maybe switch to startActivityForResult() and call finish() on OnActivityResult())
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}