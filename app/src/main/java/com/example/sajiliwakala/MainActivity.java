package com.example.sajiliwakala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button sajiliWakalaButton, sajiliMtejaButton, floatWakalaButton, salioMtejaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sajiliWakalaButton = (Button) findViewById(R.id.sajili_wakala_button);
        sajiliMtejaButton = (Button) findViewById(R.id.sajili_mteja_button);
        floatWakalaButton = (Button) findViewById(R.id.float_wakala_button);
        salioMtejaButton = (Button) findViewById(R.id.salio_mteja_button);

        sajiliWakalaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WakalaRegistrationActivity.class));
            }
        });

        sajiliMtejaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomerRegistrationActivity.class));
            }
        });

        floatWakalaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FloatWakalaActivity.class));
            }
        });

        salioMtejaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SalioMtejaActivity.class));
            }
        });
    }
}
