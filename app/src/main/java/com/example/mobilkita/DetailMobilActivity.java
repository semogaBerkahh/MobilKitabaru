package com.example.mobilkita;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobilkita.R;
import com.example.mobilkita.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {  // class untuk menampilkan dara mobil activity

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;  //deklarasi sMerk, sHarga, sGambar dengan tipe data string
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")//anotasi ini untuk menonaktifkan pemeriksaan lint khusus untuk class atau metode dalam project Android Anda
    @Override
    protected void onCreate(Bundle savedInstanceState) { //method untuk membuat bundle dari layout activity_detail_mobil
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from mobil where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Avanza")) {  //perulangan untuk menampilkan daftar mobil dan gambar mobil
            sGambar = "avanza";
        } else if (sMerk.equals("Xenia")) {
            sGambar = "xenia";
        } else if (sMerk.equals("Ertiga")) {
            sGambar = "ertiga";
        } else if (sMerk.equals("APV")) {
            sGambar = "apv";
        } else if (sMerk.equals("Innova")) {
            sGambar = "innova";
        } else if (sMerk.equals("Xpander")) {
            sGambar = "xpander";
        } else if (sMerk.equals("Pregio")) {
            sGambar = "pregio";
        } else if (sMerk.equals("Elf")) {
            sGambar = "elf";
        } else if (sMerk.equals("Alphard")) {
            sGambar = "alphard";
        }

        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        setupToolbar();

    }
    //method untuk menampilkan detail mobil
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailmtr);
        toolbar.setTitle("Detail Mobil");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //method untuk option menu titik tiga
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
