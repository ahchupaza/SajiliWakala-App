package com.example.sajiliwakala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomerRegistrationActivity extends AppCompatActivity {

    private DatabaseReference dbRef;

    private EditText fname, mname, lname, birthdate, SIM_no, accBalance;
    private ProgressBar progressBar;
    private Button registerCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customers");

        fname = (EditText) findViewById(R.id.customer_f_name);
        mname = (EditText) findViewById(R.id.customer_m_name);
        lname = (EditText) findViewById(R.id.customer_l_name);
        birthdate = (EditText) findViewById(R.id.customer_birthdate);
        SIM_no = (EditText) findViewById(R.id.customer_SIM_No_);
        accBalance = (EditText) findViewById(R.id.customer_balance);

        progressBar = (ProgressBar) findViewById(R.id.customer_register_loading);

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
                new DatePickerDialog(CustomerRegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        registerCustomerButton = (Button) findViewById(R.id.customer_register_button);
        registerCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerCustomer();
            }
        });
    }

    private void registerCustomer() {
        String first_name = fname.getText().toString().trim();
        String middle_name = mname.getText().toString().trim();
        String last_name = lname.getText().toString().trim();
        String dob = birthdate.getText().toString().trim();
        String SIM = SIM_no.getText().toString().trim();
        double balance = Integer.parseInt(accBalance.getText().toString().trim());

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
        if (SIM.isEmpty()){
            SIM_no.setError("Hukujaza eneo hili");
            SIM_no.requestFocus();
            return;
        }
//        if (balance.isEmpty()){
//            accBalance.setError("Hukujaza eneo hili");
//            accBalance.requestFocus();
//            return;
//        }

        progressBar.setVisibility(View.VISIBLE);

        Customer customer =  new Customer(first_name, middle_name, last_name, dob, SIM, balance);

        dbRef.push().setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(CustomerRegistrationActivity.this, "Mteja amesajiliwa kikamilifu!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(CustomerRegistrationActivity.this, "Haujafanikisha, tafadhali jaribu tena!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}