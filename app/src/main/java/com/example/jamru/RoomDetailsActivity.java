package com.example.jamru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamru.Model.Model;
import com.example.jamru.R;
import com.example.jamru.helpers.AppPreferenceManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RoomDetailsActivity extends AppCompatActivity {


    private Object Model;
    private Button booknow;
    AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }


        setContentView(R.layout.activity_room_details);

        Toolbar toolbar = findViewById(R.id.roomdetails_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        if(getIntent().hasExtra("name")) {

            final String name = getIntent().getStringExtra("name");
            final String alamat = getIntent().getStringExtra("alamat");
            String contact = getIntent().getStringExtra("contact");
            final String price = getIntent().getStringExtra("price");
            String desc = getIntent().getStringExtra("desc");

            setTv(name,alamat,contact,price,desc);

            booknow = findViewById(R.id.booknow);
            booknow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intBookform = new Intent(RoomDetailsActivity.this, BookformActivity.class);
//                String name = getIntent().getStringExtra("name");
//                String price = getIntent().getStringExtra("priceroom");
//                String alamat = getIntent().getStringExtra("alamatroom");
                    intBookform.putExtra("nameroom", name);
                    intBookform.putExtra("priceroom", price);
                    intBookform.putExtra("alamatroom",alamat);

                    startActivity(intBookform);
                }
            });

        } else {
            Toast.makeText(this, "Intent not found", Toast.LENGTH_SHORT).show();
        }





    }

    private void setTv(String name, String alamat, String contact, String price, String desc) {
        TextView tvName = findViewById(R.id.tvnama);
        TextView tvAlamat = findViewById(R.id.tvalamat);
        TextView tvContact = findViewById(R.id.tvcontact);
        TextView tvPrice = findViewById(R.id.tvharga);
        TextView tvDesc = findViewById(R.id.tvdesc);

        tvName.setText(name);
        tvAlamat.setText(alamat);
        tvContact.setText(contact);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


        tvPrice.setText(formatRupiah.format(Double.parseDouble(price)));
        tvDesc.setText(desc);
    }


}
