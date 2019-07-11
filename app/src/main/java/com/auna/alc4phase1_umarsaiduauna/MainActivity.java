package com.auna.alc4phase1_umarsaiduauna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
MaterialButton about_alc, my_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        about_alc = findViewById(R.id.about_alc);
        my_profile = findViewById(R.id.my_profile);

        about_alc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alc_about = new Intent(MainActivity.this, About_ALC.class);
                startActivity(alc_about);
            }
        });

        my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile_my = new Intent(MainActivity.this, My_Profile.class);
                startActivity(profile_my);
            }
        });
    }
}
