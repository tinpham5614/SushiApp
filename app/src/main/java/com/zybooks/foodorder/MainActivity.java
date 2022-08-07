package com.zybooks.foodorder;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FoodItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Food> items = new ArrayList<>();
        //adding food items to the menu
        items.add(new Food("California Roll", 3.99, R.drawable.california));
        items.add(new Food("Seattle Roll",3.99,R.drawable.seattleroll));
        items.add(new Food("Salmon Roll",3.99,R.drawable.salmon));
        items.add(new Food("Tempura Roll",3.99,R.drawable.tempura));
        items.add(new Food("Dragon Roll",3.99,R.drawable.dragon));
        items.add(new Food("Soft Drink",2.99,R.drawable.drink));

        //setting up the recycler view
        adapter = new FoodItemAdapter(items,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
