package com.example.sajiliwakala;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WakalaRegistrationActivity extends AppCompatActivity {

    private DatabaseReference dbRef;

    private EditText ID_no, fname, mname, lname, birthdate, licence_no, SIM_no, code_no, TIN_no, busns_region, accBalance, PIN_no;
    private ProgressBar progressBar;
    private Button registerWakalaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakala_registration);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Wakalas");

        ID_no = (EditText) findViewById(R.id.wakala_ID_No_);
        fname = (EditText) findViewById(R.id.wakala_f_name);
        mname = (EditText) findViewById(R.id.wakala_m_name);
        lname = (EditText) findViewById(R.id.wakala_l_name);
        birthdate = (EditText) findViewById(R.id.wakala_birthdate);
        licence_no = (EditText) findViewById(R.id.wakala_licence_No_);
        SIM_no = (EditText) findViewById(R.id.wakala_SIM_No_);
        code_no = (EditText) findViewById(R.id.wakala_code_No_);
        TIN_no = (EditText) findViewById(R.id.wakala_TIN_No_);
        busns_region = (EditText) findViewById(R.id.wakala_business_region);
        accBalance = (EditText) findViewById(R.id.wakala_balance);
        PIN_no = (EditText) findViewById(R.id.wakala_PIN);

        progressBar = (ProgressBar) findViewById(R.id.wakala_register_loading);

        // Calendar Dialog
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                birthdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(WakalaRegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        registerWakalaButton = (Button) findViewById(R.id.wakala_register_button);
        registerWakalaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerWakala();
            }
        });
    }

    private void registerWakala() {
        String ID = ID_no.getText().toString().trim();
        String first_name = fname.getText().toString().trim();
        String middle_name = mname.getText().toString().trim();
        String last_name = lname.getText().toString().trim();
        String dob = birthdate.getText().toString().trim();
        String licence = licence_no.getText().toString().trim();
        String SIM = SIM_no.getText().toString().trim();
        String code = code_no.getText().toString().trim();
        String TIN = TIN_no.getText().toString().trim();
        String region = busns_region.getText().toString().trim();
        double balance = Double.parseDouble(accBalance.getText().toString().trim());
        int PIN = Integer.parseInt(PIN_no.getText().toString().trim());

        if (ID.isEmpty()){
            ID_no.setError("Haukujaza eneo hili!");
            ID_no.requestFocus();
            return;
        }
        if (first_name.isEmpty()){
            fname.setError("Haukujaza eneo hili!");
            fname.requestFocus();
            return;
        }
        if (middle_name.isEmpty()){
            mname.setError("Haukujaza eneo hili!");
            mname.requestFocus();
            return;
        }
        if (last_name.isEmpty()){
            lname.setError("Haukujaza eneo hili!");
            lname.requestFocus();
            return;
        }
        if (dob.isEmpty()){
            birthdate.setError("Hukujaza eneo hili");
            birthdate.requestFocus();
            return;
        }
        if (licence.isEmpty()){
            licence_no.setError("Haukujaza eneo hili!");
            licence_no.requestFocus();
            return;
        }
        if (SIM.isEmpty()){
            SIM_no.setError("Hukujaza eneo hili");
            SIM_no.requestFocus();
            return;
        }
        if (code.isEmpty()){
            code_no.setError("Hukujaza eneo hili");
            code_no.requestFocus();
            return;
        }
        if (TIN.isEmpty()){
            TIN_no.setError("Haukujaza eneo hili!");
            TIN_no.requestFocus();
            return;
        }
        if (region.isEmpty()){
            busns_region.setError("Hukujaza eneo hili");
            busns_region.requestFocus();
            return;
        }
//        if (balance.isEmpty()){
//            accBalance.setError("Hukujaza eneo hili");
//            accBalance.requestFocus();
//            return;
//        }
//        if (PIN.isEmpty()){
//            PIN_no.setError("Hukujaza eneo hili");
//            PIN_no.requestFocus();
//            return;
//        }

        progressBar.setVisibility(View.VISIBLE);

        Wakala wakala =  new  Wakala(ID, first_name, middle_name, last_name, dob, licence, SIM, code, TIN, region, balance, PIN);

        dbRef.push().setValue(wakala).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(WakalaRegistrationActivity.this, "Wakala amesajiliwa kikamilifu!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(WakalaRegistrationActivity.this, "Haujafanikisha, tafadhali jaribu tena!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}