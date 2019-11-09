package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add extends AppCompatActivity {
    EditText value, type;
    Button add;
    DatabaseReference databaseValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


//        databaseValues = FirebaseDatabase.getInstance().getReference("hotels");
        value = findViewById(R.id.value);
        type = findViewById(R.id.type);
        add = findViewById(R.id.add);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addValue();
//            }
//        });

    }
    private void addValue() {
        //getting the values to save
        String name = value.getText().toString().trim();
//        Editable tp = type.getText();
        int getType = Integer.parseInt(type.getText().toString());


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseValues.push().getKey();

            //creating an Artist Object
            Categories_Obj obj = new Categories_Obj(id, name, getType);

            //Saving the Artist
            databaseValues.child(id).setValue(obj);

            //setting edittext to blank again
            value.setText("");

            //displaying a success toast
            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
