package com.example.cw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
            }
            else{
                setContentView(R.layout.sign_up);
                name = findViewById(R.id.name);
                start  = findViewById(R.id.start);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(name.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Your name is required", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SharedPreferences sharedPreferences = getSharedPreferences(new Constants().getPreferances(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("firstTime", true);
                            editor.putString("name",name.getText().toString());
                            editor.apply();
                            recreate();
                        }
                    }
                });
            }
        }

        else {
            setContentView(R.layout.activity_main);
            Toast.makeText(this, "Account Verified", Toast.LENGTH_SHORT).show();
        }
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
