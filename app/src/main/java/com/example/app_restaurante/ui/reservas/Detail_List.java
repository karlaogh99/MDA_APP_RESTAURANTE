package com.example.app_restaurante.ui.reservas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app_restaurante.R;
import com.example.app_restaurante.ui.reservas.gerente.MyReservasActivity_gerente;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

public class Detail_List extends AppCompatActivity {

    private String id_document;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        TextView nombre = findViewById(R.id.numeroReserva);
        Button eliminar = findViewById(R.id.delete_reserva);

        String fecha;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                fecha= null;
            } else {
                fecha= extras.getString("reserva_id");

                String username = extras.getString("username");

                System.out.println("ID DOCUMENTO: " + extras.getString("id_document"));

                id_document = extras.getString("id_document");

                nombre.setText(fecha);
                System.out.println(fecha);
            }
        } else {
            fecha= (String) savedInstanceState.getSerializable("reserva_id");
        }

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Reservas").document( id_document )
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!" + id_document);
                                Snackbar mySnackbar = Snackbar.make(view, "DocumentSnapshot successfully deleted!" + fecha, 2000);
                                mySnackbar.show();

                                Intent intent = new Intent(Detail_List.this, MyReservasActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error deleting document", e);
                            }
                        });
            }
        });




    }


}