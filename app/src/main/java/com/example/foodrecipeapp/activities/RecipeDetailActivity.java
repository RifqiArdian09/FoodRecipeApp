package com.example.foodrecipeapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodrecipeapp.R;
import com.example.foodrecipeapp.api.ApiClient;
import com.example.foodrecipeapp.api.ApiService;
import com.example.foodrecipeapp.models.Meal;
import com.example.foodrecipeapp.models.RecipeResponse;
import com.example.foodrecipeapp.utils.Constants;
import com.google.android.material.appbar.MaterialToolbar;

// Add these Retrofit imports
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private ImageView ivMealDetail;
    private TextView tvMealName, tvMealCategory, tvIngredients, tvInstructions;
    private Button btnWatchVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        ivMealDetail = findViewById(R.id.iv_meal_detail);
        tvMealName = findViewById(R.id.tv_meal_name);
        tvMealCategory = findViewById(R.id.tv_meal_category);
        tvIngredients = findViewById(R.id.tv_ingredients);
        tvInstructions = findViewById(R.id.tv_instructions);
        btnWatchVideo = findViewById(R.id.btn_watch_video);

        setupToolbar();

        String mealId = getIntent().getStringExtra(Constants.MEAL_EXTRA);
        if (mealId != null) {
            loadMealDetails(mealId);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void loadMealDetails(String mealId) {
        ApiService apiService = ApiClient.getApiService();
        Call<RecipeResponse> call = apiService.getMealById(mealId);

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getMeals().isEmpty()) {
                    Meal meal = response.body().getMeals().get(0);
                    displayMealDetails(meal);
                } else {
                    Toast.makeText(RecipeDetailActivity.this,
                            "Failed to load recipe details",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(RecipeDetailActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayMealDetails(Meal meal) {
        // Load meal image
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.placeholder_food)
                .into(ivMealDetail);

        // Set text values
        tvMealName.setText(meal.getStrMeal());
        tvMealCategory.setText(meal.getStrCategory());
        tvIngredients.setText(meal.getIngredients());
        tvInstructions.setText(meal.getStrInstructions());

        // Handle YouTube video button
        if (meal.getStrYoutube() != null && !meal.getStrYoutube().isEmpty()) {
            btnWatchVideo.setVisibility(View.VISIBLE);
            btnWatchVideo.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(meal.getStrYoutube()));
                startActivity(intent);
            });
        } else {
            btnWatchVideo.setVisibility(View.GONE);
        }
    }
}