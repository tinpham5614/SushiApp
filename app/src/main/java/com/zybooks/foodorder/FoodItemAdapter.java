package com.zybooks.foodorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private final ArrayList<Food> foodItems;
    private final Context context;
    long then = 0;

    public FoodItemAdapter(ArrayList<Food> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;

    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item_layout, parent, false));

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.FoodItemViewHolder holder, int position) {
        Food food = foodItems.get(position);
        holder.foodImageView.setImageResource(food.getDrawableId());
        holder.foodTitleTextView.setText(String.format(Locale.ENGLISH, "%s $%.2f",
                food.getFoodTitle(), food.getCost()));

        //setting the click listener to the order Button
        holder.orderButton.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.orderCountTextView.getText().toString());

            if (count != 0) {
                String out = String.format(Locale.ENGLISH,
                        "Your food will arrive in 2 minutes!\nOrder: %s      Quantity: %d       Price: $%.2f",
                        food.getFoodTitle(), count, food.getCost() * count);

                //displaying a Snackbar to show th order status
                Snackbar.make(v, out, Snackbar.LENGTH_LONG).show();
                holder.orderCountTextView.setText("0");
                //total plates ordered
                int totalCount = Integer.parseInt(holder.totalCount.getText().toString());
                holder.totalCount.setText(String.valueOf(totalCount + count));
            } else {
                Snackbar.make(v, "Please add your food!",
                        Snackbar.LENGTH_LONG).show();
            }
        });
        //Long press feature to reset total plates
        holder.orderButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                then = (Long) System.currentTimeMillis();
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if ((((Long)System.currentTimeMillis() - then) > 1200)){
                    holder.totalCount.setText("0");
                    return true;
                }
            }
            return false;
        });
        holder.increaseButton.setOnClickListener(v -> {
            //increasing the count of the order
            int count = Integer.parseInt(holder.orderCountTextView.getText().toString());
            holder.orderCountTextView.setText(String.valueOf(count + 1));
        });
        holder.decreaseButton.setOnClickListener(v -> {
            //decreasing the count of the order only if greater than 0
            int count = Integer.parseInt(holder.orderCountTextView.getText().toString());
            if (count > 0) {
                count -= 1;
            }
            holder.orderCountTextView.setText(String.valueOf(count));
        });

    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }


    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        public final ImageView foodImageView;
        public final TextView foodTitleTextView;
        public final Button orderButton;
        public final TextView increaseButton;
        public final TextView decreaseButton;
        public final TextView orderCountTextView;
        private final TextView totalCount;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.food_image);
            foodTitleTextView = itemView.findViewById(R.id.food_name);
            orderButton = itemView.findViewById(R.id.order_button);
            increaseButton = itemView.findViewById(R.id.order_increase);
            decreaseButton = itemView.findViewById(R.id.order_decrease);
            orderCountTextView = itemView.findViewById(R.id.order_count);
            totalCount = itemView.findViewById(R.id.total_count);

        }

    }


}
