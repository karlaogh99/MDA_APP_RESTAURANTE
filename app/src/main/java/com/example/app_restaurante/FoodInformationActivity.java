package com.example.app_restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.Model.Food;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FoodInformationActivity extends AppCompatActivity {
    private EditText name;
    private EditText desc;
    private EditText price;
    private Button edit;
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        setContentView(R.layout.activity_food_information);
        name=findViewById(R.id.name_food);
        desc=findViewById(R.id.description_food);
        price=findViewById(R.id.price_food);
        edit=findViewById(R.id.edit_food);
        food=(Food)getIntent().getSerializableExtra("Food");
        name.setText(food.getName());
        desc.setText(food.getDescripcion());
        price.setText(food.getPrice());
        if(!user.getEmail().equals("yuncai@gmail.com")){
            edit.setVisibility(View.GONE);
            name.setEnabled(false);
            desc.setEnabled(false);
            price.setEnabled(false);
        }

    }

    public void edit_food(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Food").
                        document(food.getId()).
                        set(food).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        moveToDescription();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
    public void moveToDescription(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}