package com.example.kafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ListView listOptions = findViewById(R.id.list_options);
        Button buttonCart = findViewById(R.id.button_go_to_cart);

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

        listOptions.setOnItemClickListener(itemClickListener);

        buttonCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
