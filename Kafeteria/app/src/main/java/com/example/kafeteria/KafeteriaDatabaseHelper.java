package com.example.kafeteria;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KafeteriaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "kafeteria";
    private static final int DB_VERSION = 5;

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

            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "PRICE TEXT);");

            insertDrink(db, "Latte", "Delikatna kawa z dużą ilością spienionego mleka, która zachwyca łagodnym smakiem i kremową konsystencją. Idealnie sprawdzi się zarówno rano, jak i podczas popołudniowej przerwy.", R.drawable.latte, "14,00 zł");
            insertDrink(db, "Cappuccino", "Łączy intensywny aromat espresso z aksamitnym mlekiem i puszystą pianką. To doskonały wybór dla osób, które lubią wyrazisty smak kawy w harmonijnym połączeniu z mlekiem.", R.drawable.cappuccino, "12,00 zł");
            insertDrink(db, "Flat White", "Intensywna kawa na bazie espresso z aksamitnie spienionym mlekiem. Charakteryzuje się mocniejszym smakiem niż latte, ale bardziej kremową strukturą niż cappuccino. Idealna dla osób, które lubią balans między mocą kawy a delikatnością mleka.", R.drawable.flat_white, "13,50 zł");
            insertDrink(db, "Espresso", "Esencja kawowego smaku zamknięta iniewielkiej filiżance. Charakteryzuje się intensywnym aromatem, głębokim smakiem i pobudzającym działaniem.", R.drawable.espresso, "9,00 zł");
            insertDrink(db, "Americano", "Kawa o łagodniejszym smaku niż espresso. Doskonale sprawdzi się dla osób, które lubią delektować się aromatem kawy przez dłuższy czas. Zamów Americano i ciesz się klasycznym smakiem w lekkim wydaniu.", R.drawable.americano, "10,00 zł");
            insertDrink(db, "Gorąca czekolada", "Gęsta i aksamitna gorąca czekolada przygotowywana na bazie wysokiej jakości kakao i mleka. Charakteryzuje się intensywnym, głębokim smakiem oraz delikatną słodyczą, która otula od pierwszego łyku. Idealna propozycja na chłodne dni, kiedy masz ochotę na coś rozgrzewającego i deserowego.", R.drawable.czekolada, "13,00 zł");
            insertDrink(db, "Czarna herbata", "Klasyczna czarna herbata o wyrazistym aromacie i pełnym smaku, który pobudza i dodaje energii. Doskonale sprawdza się zarówno rano, jak i w ciągu dnia jako alternatywa dla kawy. Można ją pić samą lub z dodatkiem cytryny czy miodu dla łagodniejszego smaku.", R.drawable.czarna_h, "8,00 zł");
            insertDrink(db, "Owocowa herbata", "Aromatyczna herbata owocowa łącząca naturalne smaki suszonych owoców i kwiatów. Jej delikatna słodycz i intensywny zapach sprawiają, że jest idealna zarówno na ciepło, jak i na zimno. To lekka i orzeźwiająca propozycja dla każdego, kto unika kofeiny.", R.drawable.owocowa_h, "9,00 zł");
            insertDrink(db, "Lemoniada", "Orzeźwiająca lemoniada przygotowana ze świeżo wyciskanych cytrusów i wody, idealna na upalne dni. Jej lekko kwaśny smak doskonale gasi pragnienie i dodaje energii. Często wzbogacana miętą lub lodem, staje się jeszcze bardziej odświeżająca.", R.drawable.lemoniada, "12,00 zł");
            insertDrink(db, "Mrożona herbata", "Delikatna herbata podawana na zimno z kostkami lodu, która idealnie łączy lekkość i orzeźwienie. Często wzbogacana owocami lub cytryną, co nadaje jej świeży aromat. Doskonała alternatywa dla słodkich napojów gazowanych.", R.drawable.mrozona_h, "11,00 zł");

            db.execSQL("CREATE TABLE SNACK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "PRICE TEXT);");

            insertSnack(db, "Pączek z marmoladą", "Tradycyjny pączek z miękkiego, puszystego ciasta drożdżowego wypełniony słodką marmoladą owocową. Delikatna struktura i lekko chrupiąca skórka sprawiają, że jest to klasyczny deser lub dodatek do kawy. Idealny wybór dla miłośników tradycyjnych słodkości.", R.drawable.snack1, "6,50 zł");
            insertSnack(db, "Donut czekoladowy", "Miękki donut pokryty gładką, intensywną polewą czekoladową. W środku delikatne, puszyste ciasto, które idealnie komponuje się ze słodką glazurą. To szybka i przyjemna przekąska dla fanów czekolady.", R.drawable.snack2, "7,50 zł");
            insertSnack(db, "Drożdżówka z serem", "Świeżo wypiekana drożdżówka z kremowym, delikatnie słodkim nadzieniem serowym. Jej miękkie ciasto i aromatyczny zapach sprawiają, że trudno się jej oprzeć. Idealna do kawy, herbaty lub jako szybka przekąska w ciągu dnia.", R.drawable.snack3, "7,00 zł");
            insertSnack(db, "Croissant maślany", "Klasyczny francuski croissant o warstwowej, chrupiącej strukturze i maślanym aromacie. Z zewnątrz złocisty i lekko chrupiący, w środku miękki i delikatny. Doskonały na śniadanie lub jako dodatek do kawy.", R.drawable.snack4, "8,50 zł");
            insertSnack(db, "Croissant kakaowo-orzechowy", "Maślany croissant wypełniony kremem kakaowo-orzechowym o intensywnym, słodkim smaku. Połączenie chrupiącego ciasta i kremowego nadzienia tworzy deserowy charakter. Idealny dla osób lubiących słodkie, sycące wypieki.", R.drawable.snack5, "9,50 zł");
            insertSnack(db, "Jagodzianka", "Miękka drożdżówka wypełniona dużą ilością soczystych jagód, które nadają jej naturalnej słodyczy i lekkiej kwasowości. Delikatne ciasto doskonale równoważy owocowe nadzienie. To klasyczna przekąska idealna na każdą porę dnia.", R.drawable.snack6, "8,50 zł");
            insertSnack(db, "Sernik nowojorski", "Kremowy sernik o wyjątkowo gęstej i aksamitnej konsystencji, przygotowany na bazie serka śmietankowego. Spód z kruchych ciasteczek delikatnie przełamuje jego słodycz i dodaje lekkiej chrupkości przy każdym kęsie. To klasyczny deser w nowojorskim stylu, który zachwyca prostotą i elegancją smaku.", R.drawable.snack7, "12,00 zł");
            insertSnack(db, "Szarlotka na ciepło", "Tradycyjna szarlotka przygotowana z dużej ilości soczystych jabłek i aromatycznego cynamonu. Podawana na ciepło, dzięki czemu jej zapach staje się intensywny i bardzo domowy. W połączeniu z bitą śmietaną lub lodami tworzy deser idealny na chłodniejsze dni.", R.drawable.snack8, "10,50 zł");
            insertSnack(db, "Tiramisu", "Klasyczny włoski deser przygotowany z warstw nasączonych kawą biszkoptów i kremowego mascarpone. Jego struktura jest lekka, ale jednocześnie wyrazista dzięki kawowemu aromatowi i delikatnej słodyczy. Całość wykończona kakao tworzy elegancki i intensywny smak, który długo pozostaje na podniebieniu.", R.drawable.snack9, "13,00 zł");
            insertSnack(db, "Brownie czekoladowe", "Intensywnie czekoladowe ciasto o wilgotnej i cięższej strukturze, które rozpływa się w ustach. W smaku łączy głęboką gorycz gorzkiej czekolady z delikatną słodyczą. Najlepiej smakuje lekko podgrzane, szczególnie w towarzystwie lodów waniliowych lub bitej śmietany.", R.drawable.snack10, "11,50 zł");

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



        if (oldVersion < 5) {
            db.execSQL("DROP TABLE IF EXISTS CART");
            db.execSQL("CREATE TABLE CART (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ITEM_ID INTEGER, "
                    + "ITEM_TYPE TEXT, "
                    + "QUANTITY INTEGER);");
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
