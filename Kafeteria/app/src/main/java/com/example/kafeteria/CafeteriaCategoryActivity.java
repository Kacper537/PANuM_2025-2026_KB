package com.example.kafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class CafeteriaCategoryActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_category);

        ArrayAdapter<Cafeteria> listAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        Cafeteria.cafeterias);

        ListView list =
                findViewById(R.id.list_cafeterias);

        list.setAdapter(listAdapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        Intent intent =
                                new Intent(
                                        CafeteriaCategoryActivity.this,
                                        CafeteriaActivity.class);

                        intent.putExtra(
                                CafeteriaActivity.EXTRA_CAFETERIAID,
                                (int) id);

                        startActivity(intent);
                    }
                });
    }
}