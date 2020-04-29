package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.example.jamru.Model.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddRoomActivity extends AppCompatActivity {

    private Button book;
    private EditText txtName;
    private EditText txtAlamat;
    private EditText txtContact;
    private EditText txtPricehour;
    private EditText txtDesc;

    FirebaseDatabase database;
    DatabaseReference modelDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);
        Toolbar toolbar = findViewById(R.id.addroom_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));



        book = findViewById(R.id.book); //button

        database = FirebaseDatabase.getInstance();
        modelDb = database.getReference("Model");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirebase();
            }
        });


        txtName = findViewById(R.id.txtName);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtContact = findViewById(R.id.txtContact);
        txtPricehour = findViewById(R.id.txtPricehour);
        txtDesc = findViewById(R.id.txtDesc);


    }

    private void saveToFirebase() {
        String name = txtName.getText().toString();
        String alamat = txtAlamat.getText().toString();
        String contact = txtContact.getText().toString();
        String pricehour = txtPricehour.getText().toString();
        String deskripsi = txtDesc.getText().toString();


        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(alamat) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(pricehour) && !TextUtils.isEmpty(deskripsi)) {
            Model model = new Model(name,alamat,deskripsi,pricehour,contact);

            modelDb.push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddRoomActivity.this, "Room is added", Toast.LENGTH_SHORT).show();
                    txtName.setText("");
                    txtAlamat.setText("");
                    txtContact.setText("");
                    txtPricehour.setText("");
                    txtDesc.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddRoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(this, "Isi semua field.", Toast.LENGTH_SHORT).show();
        }



    }
}
