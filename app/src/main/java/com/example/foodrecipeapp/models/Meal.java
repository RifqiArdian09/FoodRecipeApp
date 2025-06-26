package com.example.foodrecipeapp.models;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("idMeal") private String idMeal;
    @SerializedName("strMeal") private String strMeal;
    @SerializedName("strCategory") private String strCategory;
    @SerializedName("strArea") private String strArea;
    @SerializedName("strInstructions") private String strInstructions;
    @SerializedName("strMealThumb") private String strMealThumb;
    @SerializedName("strYoutube") private String strYoutube;

    // Ingredients
    @SerializedName("strIngredient1")  private String strIngredient1;
    @SerializedName("strIngredient2")  private String strIngredient2;
    @SerializedName("strIngredient3")  private String strIngredient3;
    @SerializedName("strIngredient4")  private String strIngredient4;
    @SerializedName("strIngredient5")  private String strIngredient5;
    @SerializedName("strIngredient6")  private String strIngredient6;
    @SerializedName("strIngredient7")  private String strIngredient7;
    @SerializedName("strIngredient8")  private String strIngredient8;
    @SerializedName("strIngredient9")  private String strIngredient9;
    @SerializedName("strIngredient10") private String strIngredient10;
    @SerializedName("strIngredient11") private String strIngredient11;
    @SerializedName("strIngredient12") private String strIngredient12;
    @SerializedName("strIngredient13") private String strIngredient13;
    @SerializedName("strIngredient14") private String strIngredient14;
    @SerializedName("strIngredient15") private String strIngredient15;
    @SerializedName("strIngredient16") private String strIngredient16;
    @SerializedName("strIngredient17") private String strIngredient17;
    @SerializedName("strIngredient18") private String strIngredient18;
    @SerializedName("strIngredient19") private String strIngredient19;
    @SerializedName("strIngredient20") private String strIngredient20;

    // Measures
    @SerializedName("strMeasure1")  private String strMeasure1;
    @SerializedName("strMeasure2")  private String strMeasure2;
    @SerializedName("strMeasure3")  private String strMeasure3;
    @SerializedName("strMeasure4")  private String strMeasure4;
    @SerializedName("strMeasure5")  private String strMeasure5;
    @SerializedName("strMeasure6")  private String strMeasure6;
    @SerializedName("strMeasure7")  private String strMeasure7;
    @SerializedName("strMeasure8")  private String strMeasure8;
    @SerializedName("strMeasure9")  private String strMeasure9;
    @SerializedName("strMeasure10") private String strMeasure10;
    @SerializedName("strMeasure11") private String strMeasure11;
    @SerializedName("strMeasure12") private String strMeasure12;
    @SerializedName("strMeasure13") private String strMeasure13;
    @SerializedName("strMeasure14") private String strMeasure14;
    @SerializedName("strMeasure15") private String strMeasure15;
    @SerializedName("strMeasure16") private String strMeasure16;
    @SerializedName("strMeasure17") private String strMeasure17;
    @SerializedName("strMeasure18") private String strMeasure18;
    @SerializedName("strMeasure19") private String strMeasure19;
    @SerializedName("strMeasure20") private String strMeasure20;

    public String getIdMeal() { return idMeal; }
    public String getStrMeal() { return strMeal; }
    public String getStrCategory() { return strCategory; }
    public String getStrArea() { return strArea; }
    public String getStrInstructions() { return strInstructions; }
    public String getStrMealThumb() { return strMealThumb; }
    public String getStrYoutube() { return strYoutube; }

    public String getIngredients() {
        StringBuilder ingredients = new StringBuilder();
        try {
            for (int i = 1; i <= 20; i++) {
                String ingredient = (String) getClass().getDeclaredField("strIngredient" + i).get(this);
                String measure = (String) getClass().getDeclaredField("strMeasure" + i).get(this);

                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    ingredients.append("• ").append(ingredient);
                    if (measure != null && !measure.trim().isEmpty()) {
                        ingredients.append(" - ").append(measure);
                    }
                    ingredients.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients.toString();
    }
}
