package com.example.kafeteria;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KafeteriaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "kafeteria";
    private static final int DB_VERSION = 4;

    KafeteriaDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            // Tabela Napoje
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "PRICE TEXT);");

            insertDrink(db, "Latte", "Delikatna kawa z dużą ilością spienionego mleka, która zachwyca łagodnym smakiem i kremową konsystencją. Idealnie sprawdzi się zarówno rano, jak i podczas popołudniowej przerwy.", R.drawable.latte, "14,00 zł");
            insertDrink(db, "Cappuccino", "Łączy intensywny aromat espresso z aksamitnym mlekiem i puszystą pianką. To doskonały wybór dla osób, które lubią wyrazisty smak kawy w harmonijnym połączeniu z mlekiem.", R.drawable.cappuccino, "12,00 zł");
            insertDrink(db, "Espresso", "Esencja kawowego smaku zamknięta iniewielkiej filiżance. Charakteryzuje się intensywnym aromatem, głębokim smakiem i pobudzającym działaniem.", R.drawable.espresso, "9,00 zł");
            insertDrink(db, "Americano", "Kawa o łagodniejszym smaku niż espresso. Doskonale sprawdzi się dla osób, które lubią delektować się aromatem kawy przez dłuższy czas. Zamów Americano i ciesz się klasycznym smakiem w lekkim wydaniu.", R.drawable.americano, "10,00 zł");
            insertDrink(db, "Gorąca czekolada", "Gęsta i kremowa gorąca czekolada to doskonała propozycja dla miłośników słodkich napojów.", R.drawable.czekolada, "13,00 zł");
            insertDrink(db, "Czarna herbata", "Klasyczna czarna herbata zachwyca głębokim aromatem i wyrazistym smakiem.", R.drawable.czarna_h, "8,00 zł");
            insertDrink(db, "Owocowa herbata", "Herbata owocowa to połączenie naturalnych aromatów owoców i delikatnej słodyczy.", R.drawable.owocowa_h, "9,00 zł");
            insertDrink(db, "Lemoniada", "Orzeźwiająca lemoniada przygotowana z cytrusów doskonale gasi pragnienie i dodaje energii.", R.drawable.lemoniada, "12,00 zł");
            insertDrink(db, "Mrożona herbata", "Lekki i orzeźwiający napój podawany z lodem. Łączy delikatny smak herbaty z przyjemną nutą owocową.", R.drawable.mrozona_h, "11,00 zł");

            // Tabela Przekąski
            db.execSQL("CREATE TABLE SNACK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "PRICE TEXT);");

            insertSnack(db, "Pączek z marmoladą", "Tradycyjny, puszysty pączek wypełniony słodką marmoladą owocową. Delikatne ciasto i klasyczny smak sprawiają, że jest idealnym dodatkiem do porannej kawy.", R.drawable.snack1, "6,50 zł");
            insertSnack(db, "Donut czekoladowy", "Miękki donut pokryty aksamitną polewą z mlecznej czekolady. Każdy kęs łączy lekkość ciasta z intensywną słodyczą czekolady.", R.drawable.snack2, "7,50 zł");
            insertSnack(db, "Drożdżówka z serem", "Świeżo wypiekana drożdżówka z kremowym nadzieniem serowym. Jej delikatne ciasto i subtelna słodycz świetnie komponują się z aromatyczną kawą lub herbatą.", R.drawable.snack3, "7,00 zł");
            insertSnack(db, "Croissant maślany", "Chrupiący z zewnątrz i delikatny w środku francuski croissant wypiekany na maśle. Charakterystyczne warstwy ciasta nadają mu wyjątkową lekkość i aromat.", R.drawable.snack4, "8,50 zł");
            insertSnack(db, "Croissant kakaowo-orzechowy", "Maślany croissant wypełniony kremem kakaowo-orzechowym o intensywnym smaku.", R.drawable.snack5, "9,50 zł");
            insertSnack(db, "Jagodzianka", "Miękkie ciasto drożdżowe wypełnione dużą ilością soczystych jagód.", R.drawable.snack6, "8,50 zł");

            // Tabela Lokale
            db.execSQL("CREATE TABLE CAFETERIA (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADDRESS TEXT, "
                    + "OPENING_HOURS TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insertCafeteria(db, "Sweet Home & Cafe",
                    "Adres: \nul. 7 Kamienic 11, \n42-200 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek - Czwartek: 11:00-20:00 \nPiątek - Niedziela: 11:00-21:00",
                    R.drawable.cafeteria1);

            insertCafeteria(db, "Fragment Cafe",
                    "Adres: \nul. gen. Jana Henryka Dąbrowskiego 8, \n42-200 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek - Czwartek: 9:00-19:00 \nPiątek - Niedziela: 9:00-20:00",
                    R.drawable.cafeteria2);

            insertCafeteria(db, "Caffe del Corso",
                    "Adres: \nAl. Najświętszej Maryi Panny 59, \n42-200 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek - Sobota: 7:30-22:00 \nNiedziela: 9:00-22:00",
                    R.drawable.cafeteria3);

            insertCafeteria(db, "Strzykawa",
                    "Adres: \nul. gen. Jana Henryka Dąbrowskiego 4, \n42-200 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek - Piątek: 9:00-17:00 \nSobota: 10:00-15:00 \nNiedziela: Zamknięte",
                    R.drawable.cafeteria4);

            insertCafeteria(db, "CzęstoCafe",
                    "Adres: \nal. Wolności 8, \n42-217 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek - Czwartek: 8:00-20:00 \nPiątek - Sobota: 10:00-20:00 \nNiedziela: 12:00-20:00",
                    R.drawable.cafeteria5);

            insertCafeteria(db, "Dolce Caffe",
                    "Adres: \nul. Łęczycka 56, \n42-202 Częstochowa \n",
                    "Godziny otwarcia lokalu: \nPoniedziałek: Zamknięte \nWtorek - Czwartek 10:00-18:00 \nPiątek: 10:00-19:00 \nSobota: 10:00-20:00 \nNiedziela: 12:00-19:00",
                    R.drawable.cafeteria6);
        }
        if (oldVersion < 4) {
            // Przy każdej aktualizacji wersji odświeżamy tabele z produktami, 
            // ponieważ Resource ID (np. R.drawable.latte) mogą ulec zmianie 
            // po dodaniu nowych plików do projektu.
            db.execSQL("DROP TABLE IF EXISTS DRINK");
            db.execSQL("DROP TABLE IF EXISTS SNACK");
            db.execSQL("DROP TABLE IF EXISTS CAFETERIA");
            
            // Rekonstrukcja tabel i ponowne wstawienie danych (tym razem z aktualnymi R.drawable.*)
            onCreate(db);
        }
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId, String price) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        drinkValues.put("PRICE", price);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertSnack(SQLiteDatabase db, String name, String description, int resourceId, String price) {
        ContentValues snackValues = new ContentValues();
        snackValues.put("NAME", name);
        snackValues.put("DESCRIPTION", description);
        snackValues.put("IMAGE_RESOURCE_ID", resourceId);
        snackValues.put("PRICE", price);
        db.insert("SNACK", null, snackValues);
    }

    private static void insertCafeteria(SQLiteDatabase db, String name, String address, String hours, int resourceId) {
        ContentValues cafeteriaValues = new ContentValues();
        cafeteriaValues.put("NAME", name);
        cafeteriaValues.put("ADDRESS", address);
        cafeteriaValues.put("OPENING_HOURS", hours);
        cafeteriaValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("CAFETERIA", null, cafeteriaValues);
    }
}
