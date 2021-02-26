package com.example.pgc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private String storeName;
    private int storeId;

    private String productName;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ArrayList<String> showedInfo = getIntent().getStringArrayListExtra("showedInfo");

        storeId = getIntent().getIntExtra("storeId", -1);
        productId = getIntent().getIntExtra("productId", -1);

        storeName = showedInfo.get(0);
        productName = showedInfo.get(1);

        /*
            clickedStore.getNazwa()
            clickedProduct.getNazwa()
            clickedProduct.getProducent()
            clickedProduct.getIlosc_na_stanie()
            clickedProduct.getInfo_dodatkowe()
            clickedStore.getAdres()
         */

        TextView textStoreName = findViewById(R.id.textStoreName);
        TextView textProductName = findViewById(R.id.textProductName);
        TextView textProducerName = findViewById(R.id.textProducerNameValue);
        TextView textQuantity = findViewById(R.id.textQuantityValue);
        TextView textInfo = findViewById(R.id.textInfoValue);
        TextView textStoreAdress = findViewById(R.id.textStoreAdressValue);

        textStoreName.setText(storeName);
        textProductName.setText(productName);
        textProducerName.setText(showedInfo.get(2));
        textQuantity.setText(showedInfo.get(3));
        textInfo.setText(showedInfo.get(4));
        textStoreAdress.setText(showedInfo.get(5));

    }

    public void orderItem(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("storeName", storeName);
        intent.putExtra("productName", productName);
        intent.putExtra("storeId", storeId);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }
}