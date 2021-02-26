package com.example.pgc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pgc.database.AppDatabase;
import com.example.pgc.database.StoreWithProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchResultActivity extends AppCompatActivity implements CustomAdapter.ItemClickListener {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    CustomAdapter adapter;
    ArrayList<String> params = null;
    private AppDatabase db;
    private List<StoreWithProduct> storesAndProductList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        executorService.execute(() -> {
            db = AppDatabase.getInstance(getApplicationContext());

            params = getIntent().getStringArrayListExtra("queryParams");
            storesAndProductList = db.storeDAO().findStoresByProduct(params.get(0));

            ArrayList<String> list = new ArrayList<>();
            for (StoreWithProduct x : storesAndProductList) {
                String str = x.getP().getNazwa() + ", " + x.getS().getNazwa() + ", " +
                        x.getS().getAdres();
                list.add(str);
            }

            // data to populate the RecyclerView with
            runOnUiThread(() -> {
                // set up the RecyclerView
                RecyclerView recyclerView = findViewById(R.id.recyclerViewShopList);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new CustomAdapter(this, list);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
            });

        });

    }

    @Override
    public void onItemClick(View view, int position) {
        // nowe activity ze szczegolami
        executorService.execute(() -> {
            StoreWithProduct clicked = storesAndProductList.get(position);
            ArrayList<String> showedInfo = new ArrayList<>();
            showedInfo.add(clicked.getS().getNazwa());
            showedInfo.add(clicked.getP().getNazwa());
            showedInfo.add(clicked.getP().getProducent());
            showedInfo.add(String.valueOf(clicked.getP().getIlosc_na_stanie()));
            showedInfo.add(clicked.getP().getInfo_dodatkowe());
            showedInfo.add(clicked.getS().getAdres());
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putStringArrayListExtra("showedInfo", showedInfo);
            intent.putExtra("storeId", clicked.getS().getId());
            intent.putExtra("productId", clicked.getP().getId());
            startActivity(intent);
        });
    }
}