package com.example.app_restaurante.ui.reservas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_restaurante.R;
import com.example.app_restaurante.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class ReservaFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        onDestroyView();
        View rootView = inflater.inflate(R.layout.fragment_reserva,container,false);

        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.get("email").equals(auth.getCurrentUser().getEmail())){

                                if ((boolean) document.get("gerente").equals(false)) {
                                    Button button = (Button) rootView.findViewById(R.id.crear_reserva_button);
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), NewReservaActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                    Button button2 = (Button) rootView.findViewById(R.id.consultar_reservas_button);
                                    button2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), MyReservasActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                } else {
                                    Button button = (Button) rootView.findViewById(R.id.crear_reserva_button);
                                    button.setVisibility(View.GONE);

                                    Button button2 = (Button) rootView.findViewById(R.id.consultar_reservas_button);
                                    button2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), MyReservasActivity.class);
                                            startActivity(intent);
                                        }
                                    });


                                }
                        }

                            }



                    }

                } );

        return rootView;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}