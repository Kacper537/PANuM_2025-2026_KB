package com.example.kafeteria;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private ListView listCart;
    private TextView textTotal;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setTitle("Koszyk");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listCart = findViewById(R.id.list_cart);
        textTotal = findViewById(R.id.text_total);
        Button buttonOrder = findViewById(R.id.button_order);
        Button buttonClearCart = findViewById(R.id.button_clear_cart);

        SQLiteOpenHelper databaseHelper = new KafeteriaDatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        refreshCart();

        buttonOrder.setOnClickListener(v -> showOrderDialog());

        buttonClearCart.setOnClickListener(v -> {
            db.delete("CART", null, null);
            refreshCart();
            Toast.makeText(this, "Koszyk został wyczyszczony", Toast.LENGTH_SHORT).show();
        });
    }

    private void showOrderDialog() {

        View view = getLayoutInflater().inflate(R.layout.dialog_order, null);

        Spinner spinner = view.findViewById(R.id.dialog_spinner_cafeteria);
        EditText emailInput = view.findViewById(R.id.dialog_email);

        // ustaw spinner jak w Twoim loadCafeterias()
        Cursor cursor = db.query("CAFETERIA", new String[]{"_id", "NAME"}, null, null, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_spinner_item,
                cursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1},
                0
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        new AlertDialog.Builder(this)
                .setTitle("Złóż zamówienie")
                .setView(view)
                .setPositiveButton("ZAMÓW", (dialog, which) -> {

                    String email = emailInput.getText().toString();

                    Cursor selected = (Cursor) spinner.getSelectedItem();
                    String cafeteria = selected.getString(selected.getColumnIndexOrThrow("NAME"));

                    if (validateEmail(email)) {
                        String summary = prepareOrderSummary(email, cafeteria);
                        showSuccessDialog(summary);
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            Toast.makeText(this, "Podaj adres e-mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Podaj poprawny adres e-mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String prepareOrderSummary(String email, String cafeteriaName) {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("Podsumowanie zamówienia w Kafeterii:\n\n");
        orderSummary.append("Lokal: ").append(cafeteriaName).append("\n\n");
        orderSummary.append("Produkty:\n");

        List<CartItem> items = getCartItems();
        double total = 0;
        for (CartItem item : items) {
            orderSummary.append("- ").append(item.name).append(" x ").append(item.quantity)
                    .append(" (").append(item.priceStr).append(")\n");
            total += item.priceValue * item.quantity;
        }
        orderSummary.append("\nŁączna kwota: ").append(String.format("%.2f zł", total));
        return orderSummary.toString();
    }

    private void showSuccessDialog(String summary) {
        View view = getLayoutInflater().inflate(R.layout.dialog_success, null);
        AlertDialog successDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        Button shareBtn = view.findViewById(R.id.button_share_order);
        shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Moje zamówienie - Kafeteria");
            intent.putExtra(Intent.EXTRA_TEXT, summary);
            startActivity(Intent.createChooser(intent, "Udostępnij"));
        });

        successDialog.setOnDismissListener(dialog -> {
            db.delete("CART", null, null);
            refreshCart();
            finish();
        });

        successDialog.show();
    }

    private void refreshCart() {
        List<CartItem> items = getCartItems();
        adapter = new CartAdapter(items);
        listCart.setAdapter(adapter);
        updateTotal(items);
    }

    private void updateTotal(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += item.priceValue * item.quantity;
        }
        textTotal.setText(String.format("Suma: %.2f zł", total));
    }

    private List<CartItem> getCartItems() {
        List<CartItem> items = new ArrayList<>();
        Cursor cursor = db.query("CART", new String[]{"_id", "ITEM_ID", "ITEM_TYPE", "QUANTITY"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int cartId = cursor.getInt(0);
            int itemId = cursor.getInt(1);
            String type = cursor.getString(2);
            int qty = cursor.getInt(3);

            String name = "";
            String priceStr = "0,00 zł";
            Cursor itemCursor = db.query(type, new String[]{"NAME", "PRICE"}, "_id = ?", new String[]{String.valueOf(itemId)}, null, null, null);
            if (itemCursor.moveToFirst()) {
                name = itemCursor.getString(0);
                priceStr = itemCursor.getString(1);
            }
            itemCursor.close();

            double priceValue = Double.parseDouble(priceStr.replace(" zł", "").replace(",", "."));
            items.add(new CartItem(cartId, name, priceStr, priceValue, qty));
        }
        cursor.close();
        return items;
    }

    // Metoda placeOrder została zastąpiona przez prepareOrderSummary i showSuccessDialog

    private class CartItem {
        int id;
        String name;
        String priceStr;
        double priceValue;
        int quantity;

        CartItem(int id, String name, String priceStr, double priceValue, int quantity) {
            this.id = id;
            this.name = name;
            this.priceStr = priceStr;
            this.priceValue = priceValue;
            this.quantity = quantity;
        }
    }

    private class CartAdapter extends BaseAdapter {
        private List<CartItem> items;

        CartAdapter(List<CartItem> items) {
            this.items = items;
        }

        @Override
        public int getCount() { return items.size(); }
        @Override
        public Object getItem(int position) { return items.get(position); }
        @Override
        public long getItemId(int position) { return items.get(position).id; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(CartActivity.this).inflate(R.layout.cart_item, parent, false);
            }
            CartItem item = items.get(position);
            TextView name = convertView.findViewById(R.id.item_name);
            TextView price = convertView.findViewById(R.id.item_price);
            TextView qty = convertView.findViewById(R.id.item_quantity);
            Button plus = convertView.findViewById(R.id.button_plus);
            Button minus = convertView.findViewById(R.id.button_minus);

            name.setText(item.name);
            price.setText(item.priceStr);
            qty.setText(String.valueOf(item.quantity));

            plus.setOnClickListener(v -> {
                updateQuantity(item.id, item.quantity + 1);
                refreshCart();
            });

            minus.setOnClickListener(v -> {
                if (item.quantity > 1) {
                    updateQuantity(item.id, item.quantity - 1);
                } else {
                    db.delete("CART", "_id = ?", new String[]{String.valueOf(item.id)});
                }
                refreshCart();
            });

            return convertView;
        }

        private void updateQuantity(int cartId, int newQty) {
            ContentValues values = new ContentValues();
            values.put("QUANTITY", newQty);
            db.update("CART", values, "_id = ?", new String[]{String.valueOf(cartId)});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) db.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
