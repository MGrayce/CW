package com.example.cw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories extends AppCompatActivity {
    List< Categories_Obj > categoryList;
    Categories_Obj categoryData;
    private static final int MY_CONST = 123;
    private static final int RC_SIGN_IN = 100;
    EditText name;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(new Constants().getPreferances(), Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("firstTime", false)) {
            if (!sharedPreferences.getBoolean("phonevalidated", false)) {
                List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.PhoneBuilder().build());
                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(providers)
                                .setLogo(R.mipmap.ic_launcher)
                                .build(), RC_SIGN_IN);
            } else {
                setContentView(R.layout.sign_up);
                name = findViewById(R.id.name);
                start = findViewById(R.id.start);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (name.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Your name is required", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences sharedPreferences = getSharedPreferences(new Constants().getPreferances(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("firstTime", true);
                            editor.putString("name", name.getText().toString());
                            editor.apply();
                            recreate();
                            Toast.makeText(getApplicationContext(), "mu-GPS App uses your location to get you nearby places", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else {
            setContentView(R.layout.activity_categories);

//        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Application Requires your location to proceed", Toast.LENGTH_SHORT).show();
//        }
//        else {

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
                    R.drawable.ic_conference_hall);
            mFlowerList.add(categoryData);

            Category_Adap myAdapter = new Category_Adap(getApplicationContext(), mFlowerList);
            recyclerView.setAdapter(myAdapter);
//        }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),Add.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                if (resultCode == RESULT_OK) {

                    SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                    editor.putBoolean("phonevalidated", true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                        editor.apply();
                    }
                    editor.commit();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        recreate();
                    }
                }
            }
        }
}
