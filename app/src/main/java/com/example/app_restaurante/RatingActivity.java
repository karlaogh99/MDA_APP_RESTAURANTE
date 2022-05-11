package com.example.app_restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_restaurante.Model.Food;
import com.example.app_restaurante.Model.Rating;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {

    private EditText description;
    private FirebaseUser user;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        user = FirebaseAuth.getInstance().getCurrentUser();
        description=findViewById(R.id.rating_desc);
        id=user.getUid();
    }

    public void moveToDescription(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendValoration(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> ratingMap = new HashMap<>();
        ratingMap.put("Rating", description.getText().toString());
        ratingMap.put("Id", id);
        ratingMap.put("Star", "1");
        db.collection("Rating")
                .add(ratingMap)
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
}