package com.example.agriculture;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayFarmerVeee extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<VegetableModel> dataList = new ArrayList<>();
    private VegetableAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView next;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_farmer_veee);



        recyclerView = findViewById(R.id.recycleView);

        searchView=findViewById(R.id.search);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        adapter = new VegetableAdapter(DisplayFarmerVeee.this,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        // Initialize Firebase database reference

        databaseReference = FirebaseDatabase.getInstance().getReference("User"); // Replace with your Firebase database node

        // Add a ValueEventListener to fetch data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear(); // Clear the existing data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    VegetableModel data = snapshot.getValue(VegetableModel.class);
                    dataList.add(data);
                    assert data != null;
                    Log.d("Firebase", "Data retrieved: " + data.getName()); // Log the name of each retrieved item
                }

                adapter.notifyDataSetChanged();


            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Log.e("Firebase", "Data fetch failed: " + databaseError.getMessage());

            }
        });
    }

    private void filterList(String query) {

        List<VegetableModel> filterlist = new ArrayList<>();

        for (VegetableModel vModel:dataList)
        {
            if (vModel.getName().toLowerCase().contains(query.toLowerCase()))
            {
                filterlist.add(vModel);
            }
        }

        if (filterlist.isEmpty())
        {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else {

            adapter.setFilterList(filterlist);
        }
    }
}
