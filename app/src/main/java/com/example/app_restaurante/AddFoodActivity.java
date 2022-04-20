package com.example.app_restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {
    private EditText name;
    private EditText desc;
    private EditText price;
    private EditText category;
    private Button add_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        name = findViewById(R.id.name_new_food);
        desc = findViewById(R.id.desc_new_food);
        price = findViewById(R.id.price_new_food);
        category=findViewById(R.id.category_new_food);
        add_menu=findViewById(R.id.add_food);

    }

    public void add_food(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("Nombre", name.getText().toString());
        user.put("IdCategoria", category.getText().toString());
        user.put("Precio", price.getText().toString());
        user.put("Descripcion", desc.getText().toString());
        db.collection("Food")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        moveToDescription();
                        Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error adding document", e);
                    }
                });
    }
    public void moveToDescription(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}