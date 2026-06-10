package com.example.kafeteria;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResultsActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String query = getIntent().getStringExtra(EXTRA_QUERY);
        setTitle("Wyniki dla: " + query);

        TextView queryTextView = findViewById(R.id.search_query_text);
        queryTextView.setText("Szukasz: " + query);

        ListView resultsList = findViewById(R.id.list_search_results);

        SQLiteOpenHelper dbHelper = new KafeteriaDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Szukamy w DRINK i SNACK (używając UNION dla uproszczenia wyświetlania)
        // Musimy uważać na typy, żeby wiedzieć gdzie przekierować po kliknięciu.
        // Dla uproszczenia w tym widoku: szukamy tylko w jednej tabeli lub robimy bardziej złożone zapytanie.
        // Zróbmy zapytanie które łączy obie tabele z informacją o typie.
        
        String sql = "SELECT _id, NAME, 'DRINK' as TYPE FROM DRINK WHERE NAME LIKE ? " +
                     "UNION " +
                     "SELECT _id, NAME, 'SNACK' as TYPE FROM SNACK WHERE NAME LIKE ?";
        
        String wildQuery = "%" + query + "%";
        Cursor cursor = db.rawQuery(sql, new String[]{wildQuery, wildQuery});

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"NAME", "TYPE"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);

        resultsList.setAdapter(adapter);

        resultsList.setOnItemClickListener((parent, view, position, id) -> {
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            String type = c.getString(c.getColumnIndexOrThrow("TYPE"));
            int itemId = c.getInt(c.getColumnIndexOrThrow("_id"));

            Intent intent;
            if ("DRINK".equals(type)) {
                intent = new Intent(this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, itemId);
            } else {
                intent = new Intent(this, SnackActivity.class);
                intent.putExtra(SnackActivity.EXTRA_SNACKID, itemId);
            }
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}