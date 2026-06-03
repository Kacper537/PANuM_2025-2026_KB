package com.example.kafeteria;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CafeteriaActivity extends Activity {

    public static final String EXTRA_CAFETERIAID = "cafeteriaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);

        int cafeteriaId =
                (Integer) getIntent().getExtras().get(EXTRA_CAFETERIAID);

        Cafeteria cafeteria =
                Cafeteria.cafeterias[cafeteriaId];

        TextView name = findViewById(R.id.name);
        name.setText(cafeteria.getName());

        TextView description = findViewById(R.id.description);
        description.setText(cafeteria.getDescription());
    }
}