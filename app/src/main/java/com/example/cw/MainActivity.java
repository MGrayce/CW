package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_CONST = 123;
    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.PhoneBuilder().build());
//        startActivityForResult(
//                AuthUI.getInstance().createSignInIntentBuilder()
//                        .setIsSmartLockEnabled(false)
//                        .setAvailableProviders(providers)
//                        .setLogo(R.mipmap.ic_launcher)
//                        .build(), RC_SIGN_IN);
        setContentView(R.layout.activity_main);

    }
}
