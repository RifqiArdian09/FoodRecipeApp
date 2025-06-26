package com.example.foodrecipeapp.api;

import com.example.foodrecipeapp.models.CategoryResponse;
import com.example.foodrecipeapp.models.RecipeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("filter.php")
    Call<RecipeResponse> getMealsByCategory(@Query("c") String category);

    @GET("lookup.php")
    Call<RecipeResponse> getMealById(@Query("i") String id);

    @GET("categories.php")
    Call<CategoryResponse> getAllCategories();
}