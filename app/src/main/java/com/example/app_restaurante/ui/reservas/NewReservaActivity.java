package com.example.app_restaurante.ui.reservas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_restaurante.LoginActivity;
import com.example.app_restaurante.MainActivity;
import com.example.app_restaurante.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewReservaActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    Button createReserva;

    boolean encontrada_fecha_igual;

    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserva);

        auth = FirebaseAuth.getInstance();
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.textView4);
        createReserva = (Button) findViewById(R.id.crear_reserva_button);





        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date =  i2 + "/" + (i1 + 1) + "/" + i;
                myDate.setText(date);
            }
        });
    }

    public void createReserva(View view) throws ParseException {
        encontrada_fecha_igual = false;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(myDate.getText().toString());

        long time = date.getTime();
        Timestamp reserveDate = new Timestamp(time);


        FirebaseUser currentUser = auth.getCurrentUser();

        Map<String, Object> reserva = new HashMap<>();
        reserva.put("date", reserveDate);
        reserva.put("username", currentUser.getEmail());
        reserva.put("gerente", false);


        db.collection("Reservas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("EROR EN FUNCION");
                            }
                        });
                        if (task.isSuccessful() && getIntent()!=null) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());

                                com.google.firebase.Timestamp timestamp = (com.google.firebase.Timestamp) document.get("date");

                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.K");
                                String format = formatter.format(timestamp.toDate());


                                System.out.println(format);
                                System.out.println(reserveDate);

                                if(format.equals(reserveDate.toString())){
                                    if(document.get("username").equals(auth.getCurrentUser().getEmail())){
                                        System.out.println("IGUALES");
                                        encontrada_fecha_igual = true;
                                        Snackbar mySnackbar = Snackbar.make(view, "Seleccione otro día, ya tiene reserva para este dia", 5000);
                                        mySnackbar.show();
                                        break;
                                    }

                                }


                                System.out.println("ENTRANDO");

                            }

                            if(encontrada_fecha_igual == false){
                                insertar(reserva);
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }


                });



}

public void insertar(Map<String, Object> reserva ){

    db.collection("Reservas")
            .add(reserva)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("", "Reserva added with ID: " + documentReference.getId());
                    startActivity(new Intent(NewReservaActivity.this, ReservaFragment.class));
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("", "Error adding reserva", e);
                }
            });
}
}