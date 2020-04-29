package com.example.jamru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamru.helpers.AppPreferenceManager;

import java.text.NumberFormat;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {

    private TextView invoicebookername;
    private TextView invoiceroomname;
    private TextView invoiceroomalamat;
    private TextView invoiceroomprice;
    private TextView invoiceroomdurasi;
    private TextView invoiceroomtotal;
    AppPreferenceManager preferenceManager;
    private Button buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }


        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = findViewById(R.id.invoice_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        buttonOK = findViewById(R.id.endinvoice);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intOK = new Intent(InvoiceActivity.this, BooknowActivity.class);
                startActivity(intOK);
                finish();
            }
        });

        invoicebookername = findViewById(R.id.invoicename);
        invoiceroomname = findViewById(R.id.invoiceroom);
        invoiceroomalamat = findViewById(R.id.invoicemeetingroom);
        invoiceroomprice = findViewById(R.id.invoiceprice);
        invoiceroomdurasi = findViewById(R.id.invoicedurasi);
        invoiceroomtotal = findViewById(R.id.invoicetotal);


        if (getIntent().hasExtra("bookername")) {
            invoicebookername.setText(getIntent().getStringExtra("bookername"));
            invoiceroomname.setText(getIntent().getStringExtra("roomname"));
            invoiceroomalamat.setText(getIntent().getStringExtra("roomalamat"));
            invoiceroomprice.setText("Tarif : Rp. "+getIntent().getStringExtra("roomprice"));
            invoiceroomdurasi.setText("Durasi : "+getIntent().getStringExtra("roomdurasi")+" Jam");
//            invoiceroomprice.setText(getIntent().getStringExtra("roomprice"));

            String priceInvoice = getIntent().getStringExtra("roomprice");
            String durasiInvoice = getIntent().getStringExtra("roomdurasi");

            Double mPriceInvoice = Double.parseDouble(getIntent().getStringExtra("roomprice"));
            Double mDurasiInvoice = Double.parseDouble(getIntent().getStringExtra("roomdurasi"));

            double sum = mPriceInvoice * mDurasiInvoice;
//            invoiceroomtotal.setText(Double.toString(sum));

            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            invoiceroomtotal.setText(formatRupiah.format((double)sum));



//            Integer tes = calculatePrice(mPriceInvoice,mDurasiInvoice);
//            invoiceroomprice.setText(tes);


        } else {
            Toast.makeText(this, "Intent not found sorry", Toast.LENGTH_SHORT).show();
        }

    }



    private int calculatePrice(int price, int durasi) {
        int total = price+durasi;
        return total;
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
