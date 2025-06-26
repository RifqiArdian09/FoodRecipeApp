package com.example.foodrecipeapp.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.R;
import com.example.foodrecipeapp.adapters.CategoryAdapter;
import com.example.foodrecipeapp.adapters.RecipeAdapter;
import com.example.foodrecipeapp.api.ApiClient; // Added this import
import com.example.foodrecipeapp.api.ApiService;
import com.example.foodrecipeapp.models.CategoryResponse;
import com.example.foodrecipeapp.models.RecipeResponse;
import com.example.foodrecipeapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPopularRecipes, rvCategories;
    private RecipeAdapter recipeAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupRecyclerViews();
        loadPopularRecipes();
        loadCategories();
    }

    private void initializeViews() {
        rvPopularRecipes = findViewById(R.id.rv_popular_recipes);
        rvCategories = findViewById(R.id.rv_categories);
    }

    private void setupRecyclerViews() {
        // Popular recipes - horizontal layout
        rvPopularRecipes.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        recipeAdapter = new RecipeAdapter(this, null);
        rvPopularRecipes.setAdapter(recipeAdapter);

        // Categories - grid layout
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        categoryAdapter = new CategoryAdapter(this, null);
        rvCategories.setAdapter(categoryAdapter);
    }

    private void loadPopularRecipes() {
        ApiService apiService = ApiClient.getApiService();
        Call<RecipeResponse> call = apiService.getMealsByCategory(Constants.DEFAULT_CATEGORY);

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipeAdapter.updateData(response.body().getMeals());
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load recipes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCategories() {
        ApiService apiService = ApiClient.getApiService();
        Call<CategoryResponse> call = apiService.getAllCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryAdapter.updateData(response.body().getCategories());
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}