package com.example.app_restaurante.ui.reservas.gerente;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_restaurante.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyReservasActivity_gerente extends AppCompatActivity {

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



        db.collection("Reservas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()  && getIntent()!=null) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                    Timestamp timestamp = (Timestamp) document.get("date");


                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    String format = formatter.format(timestamp.toDate());



                                    list.add(format + " - " + document.get("username"));

                                    arrayAdapter = new ArrayAdapter(MyReservasActivity_gerente.this, android.R.layout.simple_list_item_1, list);
                                    listView.setAdapter(arrayAdapter);
                                    Toast.makeText(MyReservasActivity_gerente.this, "Inserted", Toast.LENGTH_LONG).show();



                            }


                        }
                    }

                } );

    }
}