package com.example.app_restaurante.ui.reservas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_restaurante.R;
import com.example.app_restaurante.databinding.FragmentHomeBinding;

public class ReservaFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        onDestroyView();
        View rootView = inflater.inflate(R.layout.fragment_reserva,container,false);

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

        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}