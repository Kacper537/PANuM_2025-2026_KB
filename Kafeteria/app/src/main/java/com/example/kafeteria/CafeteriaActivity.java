package com.example.kafeteria;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CafeteriaActivity extends AppCompatActivity {

    public static final String EXTRA_CAFETERIAID = "cafeteriaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int cafeteriaId = (Integer) getIntent().getExtras().get(EXTRA_CAFETERIAID);

        SQLiteOpenHelper databaseHelper = new KafeteriaDatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.query("CAFETERIA",
                    new String[]{"NAME", "ADDRESS", "OPENING_HOURS", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(cafeteriaId)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                setTitle(nameText);
                String addressText = cursor.getString(1);
                String openingHoursText = cursor.getString(2);
                int photoId = cursor.getInt(3);

                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView address = findViewById(R.id.address);
                address.setText(addressText);

                TextView openingHours = findViewById(R.id.opening_hours);
                openingHours.setText(openingHoursText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
