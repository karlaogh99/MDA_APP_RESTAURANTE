package com.example.app_restaurante.ui.reservas.gerente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_restaurante.R;
import com.example.app_restaurante.ui.reservas.MyReservasActivity;

public class ReservaFragmentGerente extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        onDestroyView();
        View rootView = inflater.inflate(R.layout.fragment_reserva_gerente, container, false);

        Button button2 = (Button) rootView.findViewById(R.id.consultar_reservas_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyReservasActivity_gerente.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}