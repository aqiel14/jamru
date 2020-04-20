package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jamru.Model.Model;
import com.example.jamru.Model.ModelBooking;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookformActivity extends AppCompatActivity {


    private Button book;
    private EditText txtRoomName;
    private EditText txtBookerName;
    private EditText txtBookerContact;

    private EditText txtDate;
    private Button btnSetDate;


    private EditText txtTime;
    private Button btnStartTime;



    private int mYear, mMonth, mDay, mHour, mMinute;
    private Button btnCreateBook;

    FirebaseDatabase database;
    DatabaseReference modelBookingDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookform);

        txtRoomName = findViewById(R.id.txtRoomName);
        txtBookerName = findViewById(R.id.txtBookerName);
        txtBookerContact = findViewById(R.id.txtBookerContact);

        btnSetDate = findViewById(R.id.btnDate);
        btnStartTime=findViewById(R.id.btnStart);


        txtDate = findViewById(R.id.txt_date);
        txtTime=findViewById(R.id.txt_startTime);
        btnCreateBook = findViewById(R.id.createBooking);

        final String nameroom = getIntent().getStringExtra("nameroom");
        txtRoomName.setText(nameroom);
        txtRoomName.setEnabled(false);
        txtDate.setEnabled(false);
        txtTime.setEnabled(false);

        final String alamad = getIntent().getStringExtra("alamatroom");
        final String priceroom = getIntent().getStringExtra("priceroom");




        btnSetDate.setOnClickListener(new View.OnClickListener() { //ketika tombol set Date diklik
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookformActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookformActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        database = FirebaseDatabase.getInstance();
        modelBookingDb = database.getReference("ModelBooking");





        btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirebase();


                Intent intInvoice = new Intent(BookformActivity.this, InvoiceActivity.class);
                intInvoice.putExtra("bookername",txtBookerName.getText().toString());
                intInvoice.putExtra("roomname", txtRoomName.getText().toString());
                intInvoice.putExtra("roomprice", priceroom);
                intInvoice.putExtra("roomalamat", alamad);
                intInvoice.putExtra("roomdurasi", ((Spinner)findViewById(R.id.SpinnerDurasi)).getSelectedItem().toString());
                startActivity(intInvoice);
            }
        });





    }

    private void saveToFirebase() {
        String roomname = txtRoomName.getText().toString();
        String bookername = txtBookerName.getText().toString();
        String keperluan = ((Spinner)findViewById(R.id.spinnerKeperluan)).getSelectedItem().toString();
        String bookercontact = txtBookerContact.getText().toString();
        String bookdate = txtDate.getText().toString();
        String booktime = txtTime.getText().toString();
        String durasi = ((Spinner)findViewById(R.id.SpinnerDurasi)).getSelectedItem().toString();


        if(!TextUtils.isEmpty(roomname) && !TextUtils.isEmpty(bookername) && !TextUtils.isEmpty(keperluan) && !TextUtils.isEmpty(bookercontact) && !TextUtils.isEmpty(bookdate) &&
                !TextUtils.isEmpty(booktime) && !TextUtils.isEmpty(durasi))
        {
            ModelBooking model = new ModelBooking(roomname,bookername,keperluan,bookercontact,bookdate,booktime,durasi);

            modelBookingDb.push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(BookformActivity.this, "Booking is made", Toast.LENGTH_SHORT).show();
                    txtRoomName.setText("");
                    txtBookerName.setText("");
                    txtBookerContact.setText("");
                    txtDate.setText("");
                    txtTime.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BookformActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(this, "Isi semua field.", Toast.LENGTH_SHORT).show();
        }



    }



}
