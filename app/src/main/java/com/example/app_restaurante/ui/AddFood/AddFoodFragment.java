package com.example.app_restaurante.ui.AddFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_restaurante.AddFoodActivity;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.R;

public class AddFoodFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_add_food_fragment,container,false);
        moveToDescription();
        return root;
    }
    public void moveToDescription(){
        Intent intent= new Intent(getActivity(), AddFoodActivity.class);
        startActivity(intent);
    }
}