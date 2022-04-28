package com.example.app_restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_restaurante.Adapter.AdapterCategory;
import com.example.app_restaurante.Adapter.AdapterFood;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.Model.Food;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {
    RecyclerView recycler_menu;
    ArrayList<Food> listFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Read from the database
        listFood=new ArrayList<>();
        recycler_menu=(RecyclerView)findViewById(R.id.recycler_food);
        recycler_menu.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        db.collection("Food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && getIntent()!=null) {
                            Category catId=(Category)getIntent().getSerializableExtra("Categoria");

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(catId.getName().equals((String)document.get("idcategory"))){
                                    Food food=new Food("","","","","");
                                    food.setName((String) document.get("name"));
                                    food.setDescripcion((String) document.get("description"));
                                    food.idcategory=((String) document.get("idcategory"));
                                    food.setPrice((String) document.get("price"));
                                    food.setId((String) document.getId());

                                    listFood.add(food);
                                }


                            }
                            AdapterFood adapter=new AdapterFood(listFood, new AdapterFood.OnItemClickListener() {
                                @Override
                                public void onItemClick(Food food) {
                                    moveToDescription(food);
                                }
                            });
                            recycler_menu.setAdapter(adapter);
                        }
                    }
                });

    }
    public void moveToDescription(Food food){
        Intent intent= new Intent(this, FoodInformationActivity.class);
        intent.putExtra("Food", food);
        startActivity(intent);
    }
}