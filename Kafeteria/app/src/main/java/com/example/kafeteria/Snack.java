package com.example.kafeteria;

public class Snack {

    private String name;
    private String description;
    private String price;
    private int imageResourceId;

    public static final Snack[] snacks = {

            new Snack(
                    "Pączek z marmoladą",
                    "Tradycyjny, puszysty pączek wypełniony słodką marmoladą owocową. Delikatne ciasto i klasyczny smak sprawiają, że jest idealnym dodatkiem do porannej kawy. Spróbuj i przekonaj się, dlaczego to jeden z najpopularniejszych wypieków.",
                    "6,50 zł",
                    R.drawable.snack1),

            new Snack(
                    "Donut czekoladowy",
                    "Miękki donut pokryty aksamitną polewą z mlecznej czekolady. Każdy kęs łączy lekkość ciasta z intensywną słodyczą czekolady. Doskonały wybór na chwilę przyjemności podczas przerwy w ciągu dnia.",
                    "7,50 zł",
                    R.drawable.snack2),

            new Snack(
                    "Drożdżówka z serem",
                    "Świeżo wypiekana drożdżówka z kremowym nadzieniem serowym. Jej delikatne ciasto i subtelna słodycz świetnie komponują się z aromatyczną kawą lub herbatą. To klasyka, do której zawsze chce się wracać.",
                    "7,00 zł",
                    R.drawable.snack3),

            new Snack(
                    "Croissant maślany",
                    "Chrupiący z zewnątrz i delikatny w środku francuski croissant wypiekany na maśle. Charakterystyczne warstwy ciasta nadają mu wyjątkową lekkość i aromat. Idealny zarówno na śniadanie, jak i popołudniową przekąskę.",
                    "8,50 zł",
                    R.drawable.snack4),

            new Snack(
                    "Croissant kakaowo-orzechowy",
                    "Maślany croissant wypełniony kremem kakaowo-orzechowym o intensywnym smaku. Połączenie chrupiącego ciasta i aksamitnego nadzienia zachwyci każdego miłośnika słodkości. Najlepiej smakuje w towarzystwie filiżanki cappuccino lub latte.",
                    "9,50 zł",
                    R.drawable.snack5),

            new Snack(
                    "Jagodzianka",
                    "Miękkie ciasto drożdżowe wypełnione dużą ilością soczystych jagód. Naturalna słodycz owoców i delikatne ciasto tworzą wyjątkowo udane połączenie. To jedna z tych przekąsek, po które trudno nie sięgnąć ponownie.",
                    "8,50 zł",
                    R.drawable.snack6),
    };

    private Snack(String name,
                  String description,
                  String price,
                  int imageResourceId) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    @Override
    public String toString() {
        return name;
    }
}