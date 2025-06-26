package com.example.foodrecipeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecipeapp.R;
import com.example.foodrecipeapp.activities.RecipeDetailActivity;
import com.example.foodrecipeapp.models.Meal;
import com.example.foodrecipeapp.utils.Constants;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context context;
    private List<Meal> mealList;

    public RecipeAdapter(Context context, List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.placeholder_food)
                .into(holder.mealImage);

        holder.mealName.setText(meal.getStrMeal());
        holder.mealCategory.setText(meal.getStrCategory());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra(Constants.MEAL_EXTRA, meal.getIdMeal());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mealList != null ? mealList.size() : 0;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName, mealCategory;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.iv_meal);
            mealName = itemView.findViewById(R.id.tv_meal_name);
            mealCategory = itemView.findViewById(R.id.tv_meal_category);
        }
    }

    public void updateData(List<Meal> newMealList) {
        this.mealList = newMealList;
        notifyDataSetChanged();
    }
}