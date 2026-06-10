package com.example.kafeteria;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);

        SQLiteOpenHelper databaseHelper = new KafeteriaDatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "PRICE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                setTitle(nameText);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                String priceText = cursor.getString(3);

                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                TextView price = findViewById(R.id.price);
                price.setText(priceText);

                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                Button addToCart = findViewById(R.id.button_add_to_cart);
                addToCart.setOnClickListener(v -> {
                    addToCart(drinkId, "DRINK");
                    Toast.makeText(this, "Dodano do koszyka!", Toast.LENGTH_SHORT).show();
                });
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_SHORT).show();
        }
    }

    private void addToCart(int itemId, String type) {
        SQLiteOpenHelper databaseHelper = new KafeteriaDatabaseHelper(this);
        try (SQLiteDatabase db = databaseHelper.getWritableDatabase()) {
            // Sprawdzenie czy produkt jest już w koszyku
            Cursor cursor = db.query("CART", new String[]{"_id", "QUANTITY"},
                    "ITEM_ID = ? AND ITEM_TYPE = ?",
                    new String[]{String.valueOf(itemId), type},
                    null, null, null);

            if (cursor.moveToFirst()) {
                int cartId = cursor.getInt(0);
                int currentQty = cursor.getInt(1);
                ContentValues values = new ContentValues();
                values.put("QUANTITY", currentQty + 1);
                db.update("CART", values, "_id = ?", new String[]{String.valueOf(cartId)});
            } else {
                ContentValues values = new ContentValues();
                values.put("ITEM_ID", itemId);
                values.put("ITEM_TYPE", type);
                values.put("QUANTITY", 1);
                db.insert("CART", null, values);
            }
            cursor.close();
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
