package com.example.app_restaurante.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_restaurante.Adapter.AdapterCategory;
import com.example.app_restaurante.FoodListActivity;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.R;
import com.example.app_restaurante.databinding.FragmentSlideshowBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private RecyclerView recycler_menu;
    ArrayList<Category> listCategory;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_slideshow,container,false);
        listCategory=new ArrayList<>();
        recycler_menu=(RecyclerView)getActivity().findViewById(R.id.menu);
        recycler_menu.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_menu.setLayoutManager(layoutManager);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category cat=new Category("","");
                                cat.setName((String) document.get("Nombre"));
                                cat.setImage((String)document.get("Image"));

                                listCategory.add(cat);

                            }
                            AdapterCategory adapter=new AdapterCategory(listCategory, new AdapterCategory.OnItemClickListener() {
                                @Override
                                public void onItemClick(Category cat) {
                                    moveToDescription(cat);
                                }
                            });
                            recycler_menu.setAdapter(adapter);


                        }
                    }
                });

        return root;
    }
    public void moveToDescription(Category cat){
        Intent intent= new Intent(getActivity(), FoodListActivity.class);
        intent.putExtra("Categoria", cat);
        startActivity(intent);
    }
}