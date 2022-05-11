package com.example.app_restaurante.ui.reservas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_restaurante.Adapter.AdapterFood;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.Model.Food;
import com.example.app_restaurante.R;
import com.example.app_restaurante.ui.reservas.gerente.MyReservasActivity_gerente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyReservasActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;

    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservas);

        auth = FirebaseAuth.getInstance();
        listView = findViewById(R.id.listaReservas);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list_id = new ArrayList<>();


        db.collection("Reservas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()  && getIntent()!=null) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if(document.get("username").equals(auth.getCurrentUser().getEmail())){

                                    Timestamp timestamp = (Timestamp) document.get("date");


                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    String format = formatter.format(timestamp.toDate());



                                    list.add(format);
                                    list_id.add(document.getId());



                                    arrayAdapter = new ArrayAdapter(MyReservasActivity.this, android.R.layout.simple_list_item_1, list);
                                    listView.setAdapter(arrayAdapter);

                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            System.out.println("Item clicked at: " + i  + " fecha " + adapterView.getItemAtPosition(i).toString() );

                                            //get selected items
                                            String selectedValue = (String) adapterView.getItemAtPosition(i).toString();



                                            Intent intent = new Intent (getApplicationContext(), Detail_List.class);
                                            intent.putExtra("reserva_id", selectedValue);
                                            intent.putExtra("username", document.get("username").toString());
                                            intent.putExtra("id_document", list_id.get(i));


                                            startActivity(intent);
                                        }
                                    });

                                    Toast.makeText(MyReservasActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                                }


                            }


                        }
                    }

                } );

    }
}