package com.example.foodrecipeapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.R;
import com.example.foodrecipeapp.adapters.RecipeAdapter;
import com.example.foodrecipeapp.api.ApiClient;  // THIS IS THE IMPORT YOU NEED
import com.example.foodrecipeapp.api.ApiService;
import com.example.foodrecipeapp.models.RecipeResponse;
import com.example.foodrecipeapp.utils.Constants;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView rvCategoryMeals;
    private RecipeAdapter recipeAdapter;
    private TextView tvCategoryTitle;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initializeViews();
        setupRecyclerView();
        setupToolbar();

        String category = getIntent().getStringExtra(Constants.CATEGORY_EXTRA);
        if (category != null) {
            tvCategoryTitle.setText(category);
            loadMealsByCategory(category);
        }
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        rvCategoryMeals = findViewById(R.id.rv_category_meals);
        tvCategoryTitle = findViewById(R.id.tv_category_title);
    }

    private void setupRecyclerView() {
        rvCategoryMeals.setLayoutManager(new GridLayoutManager(this, 2));
        recipeAdapter = new RecipeAdapter(this, null);
        rvCategoryMeals.setAdapter(recipeAdapter);
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

    private void loadMealsByCategory(String category) {
        ApiService apiService = ApiClient.getApiService();
        Call<RecipeResponse> call = apiService.getMealsByCategory(category);

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipeAdapter.updateData(response.body().getMeals());
                } else {
                    Toast.makeText(CategoryActivity.this, "Failed to load meals", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}