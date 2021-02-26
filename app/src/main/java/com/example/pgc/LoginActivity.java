package com.example.pgc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pgc.database.AppDatabase;
import com.example.pgc.database.Product;
import com.example.pgc.database.Review;
import com.example.pgc.database.Store;
import com.example.pgc.database.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private AppDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getInstance(getApplicationContext());

    }

    public void loginUser(View view) {
        // ukryj klawe
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // pobierz dane z pol login i haslo
        EditText loginEditTextView = findViewById(R.id.editTextLogin);
        EditText passwordEditTextView = findViewById(R.id.editTextPassword);
        String login = loginEditTextView.getText().toString();
        String password = passwordEditTextView.getText().toString();

        // w nowym watku...
        executorService.execute(() -> {
            // zaladuj uzytkownika z bazy o podanym loginie
            User test = db.userDAO().loadUserByName(login);
            // jesli istnieje i poprawne haslo...
            if (test != null && test.getPassword().equals(password)) {
                // ustaw flage zalogowano na 1
                SharedPreferences settings = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("logged", 1);
                editor.putInt("userId", test.getId());
                editor.apply();

                // przejdz do glownej aktywnosci i zakoncz obecna
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

                // jesli nie istnieje lub zle haslo...
            } else
                // wyswietl info
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Błędne dane logowania!", Toast.LENGTH_SHORT).show());
        });

    }

    public void initDb(View view) {
        executorService.execute(() -> {

            User karo = new User(1, "karo", "123");
            User martika = new User(2, "martika", "123");
            User kamil = new User(3, "kamil", "123");

            User[] users = {karo, martika, kamil};
            for (User u : users) {
                try {
                    db.userDAO().insert(u);
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, String.format("Błąd przy dodawaniu użytkownika %s!", u.getUsername()), Toast.LENGTH_SHORT).show());
                }
            }


            Store s1 = new Store("Żapka", "Wołoska 141A", 0.f);
            Store s2 = new Store("Frog Market", "Kononowska 13", 0.f);

            Store[] stores = {s1, s2};
            for (Store s : stores) {
                try {
                    db.storeDAO().insert(s);
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, String.format("Błąd przy dodaniu sklepu %s, %s!", s.getNazwa(), s.getAdres()), Toast.LENGTH_SHORT).show());
                }
            }


            Review r11 = new Review("1", "Fajny taki nawet. 1/1.", 1.f, "AnonXD");
            Review r12 = new Review("1", "Kasjerka nie powiedziała dzień dobry. 0/1.", 0.f, "Halina");
            Review r23 = new Review("2", "Piwo Specjal smakuje jak zwykły Harnaś. 0.5/1.", 5.f, "Krysia43");

            Review[] reviews = {r11, r12, r23};
            for (Review r : reviews) {
                try {
                    db.reviewDAO().insert(r);
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, String.format("Błąd przy dodaniu opinii %s!", r.getAutor()), Toast.LENGTH_SHORT).show());
                }
            }


            Product p11 = new Product("1", "skarpetki krótkie", "nike", 20, "Konfortowe sportowe skiety");
            Product p12 = new Product("1", "skarpetki długie", "nike", 25, "Konfortowe sportowe skiety ale dluzsze");
            Product p13 = new Product("1", "kawa czarna", "jacobs", 15, "Pyszna kawusia");
            Product p21 = new Product("2", "skarpetki krótkie", "nike", 30, "Zwykle skiety");
            Product p22 = new Product("2", "skarpetki długie", "adidas", 35, "Zwykle skiety ale dluzsze");
            Product p23 = new Product("2", "kawa czarna", "tchibo", 20, "Aromatyczna kawusia");

            Product[] products = {p11, p12, p13, p21, p22, p23};
            for (Product p : products) {
                try {
                    db.productDAO().insert(p);
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, String.format("Błąd przy dodaniu produktu %s, %s!", p.getNazwa(), p.getStoreId()), Toast.LENGTH_SHORT).show());
                }
            }

        });
    }
}