package com.example.kafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Kafeteria");
        }

        // if (getSupportActionBar() != null) {
        //     getSupportActionBar().hide();
        // }

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        if(position == 0) {
                            Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                            startActivity(intent);
                        }
                        else if(position == 1) {
                            Intent intent = new Intent(MainActivity.this, SnackCategoryActivity.class);
                            startActivity(intent);
                        }
                        else if(position == 2) {
                            Intent intent = new Intent(MainActivity.this, CafeteriaCategoryActivity.class);
                            startActivity(intent);
                        }
                    }
                };

        Button buttonDrinks = findViewById(R.id.button_drinks);
        Button buttonSnacks = findViewById(R.id.button_snacks);
        Button buttonCafeterias = findViewById(R.id.button_cafeterias);

        buttonDrinks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
            startActivity(intent);
        });

        buttonSnacks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SnackCategoryActivity.class);
            startActivity(intent);
        });

        buttonCafeterias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CafeteriaCategoryActivity.class);
            startActivity(intent);
        });

//        buttonCart.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CartActivity.class);
//            startActivity(intent);
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setQueryHint("Szukaj produktów...");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        // Tutaj logika wyszukiwania w bazie
                        performSearch(query);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }
        }

        return true;
    }

    private void performSearch(String query) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra(SearchResultsActivity.EXTRA_QUERY, query);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }

        if (id == R.id.action_search) {
            // wyszukiwarka
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
