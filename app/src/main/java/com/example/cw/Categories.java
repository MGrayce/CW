package com.example.cw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity {
    List< Categories_Obj > categoryList;
    Categories_Obj categoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

//        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Application Requires your location to proceed", Toast.LENGTH_SHORT).show();
//        }
//        else {

        Toast.makeText(this, "mu-GPS App uses your location to get you nearby places", Toast.LENGTH_SHORT).show();
            RecyclerView recyclerView = findViewById(R.id.rec);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mGridLayoutManager);

            List<Categories_Obj> mFlowerList = new ArrayList<>();
            categoryData = new Categories_Obj("Hotels", getString(R.string.description_hotel),
                    R.drawable.ic_hot);
            mFlowerList.add(categoryData);
            categoryData = new Categories_Obj("Boutiques", getString(R.string.description_boutique),
                    R.drawable.ic_bot);
            mFlowerList.add(categoryData);
            categoryData = new Categories_Obj("Clubs", getString(R.string.description_club),
                    R.drawable.ic_con);
            mFlowerList.add(categoryData);
            categoryData = new Categories_Obj("Cinemas", getString(R.string.description_bar),
                    R.drawable.ic_m);
            mFlowerList.add(categoryData);

            Category_Adap myAdapter = new Category_Adap(getApplicationContext(), mFlowerList);
            recyclerView.setAdapter(myAdapter);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent info = new Intent(getApplicationContext(),Add.class);
        startActivity(info);
        return super.onOptionsItemSelected(item);
    }
}
