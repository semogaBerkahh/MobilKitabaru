package com.example.mobilkita;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilkita.R;

import java.util.Calendar;
import java.util.Date;
//public class main activity untuk extends app compat activity
public class MainActivity extends AppCompatActivity {
    //mendeklarasikan variabel texview, string, dan animation dengan nama sebagai berikut.
    TextView tvToday, tvMainSalam;
    String hariIni;
    Animation animTv;

    @Override //anotasi override berfungsi untuk membantu memeriksa apakah harus mengganti metode pada kelas induk
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code untuk menggabungkan dengan button info mobil dan button sewa
        Button informasi = findViewById(R.id.btn_info_mobil);
        Button sewa = findViewById(R.id.btn_sewa);

        informasi.setOnClickListener(new View.OnClickListener() {  //memanggil data informasi daftar mobil
            @Override
            public void onClick(View v) { //meethod on click yang diambil untuk mengambil tampilan ketika di klik daro class main activity dan daftarmobilactivity
                Intent i = new Intent(MainActivity.this, DaftarMobilActivity.class);
                startActivity(i);
            }
        });
        //Metode ini dipanggil bila pengguna menyentuh item untuk menampilkan tampilan sewa
        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MainActivity.this, PenyewaActivity.class);
                startActivity(p);
            }
        });
        //kode ini digunakanuntuk menampilkan tampilan today, salam, anim tv, dan mainsalam
        tvToday = findViewById(R.id.tvDate);
        tvMainSalam = findViewById(R.id.tvMainSalam);
        animTv = AnimationUtils.loadAnimation(this, R.anim.anim_tv);
        tvMainSalam.startAnimation(animTv);
        //kode perulangan ini digunakan untuk menampilkan hari
        Date dateNow = Calendar.getInstance().getTime();  //untuk menampilkan hari dengan memanggil data terkini dari calender dengan getTime
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")) {
            hariIni = "Minggu";
        } else if (hariIni.equalsIgnoreCase("monday")) {
            hariIni = "Senin";
        } else if (hariIni.equalsIgnoreCase("tuesday")) {
            hariIni = "Selasa";
        } else if (hariIni.equalsIgnoreCase("wednesday")) {
            hariIni = "Rabu";
        } else if (hariIni.equalsIgnoreCase("thursday")) {
            hariIni = "Kamis";
        } else if (hariIni.equalsIgnoreCase("friday")) {
            hariIni = "Jumat";
        } else if (hariIni.equalsIgnoreCase("saturday")) {
            hariIni = "Sabtu";
        }

        getToday();
        setSalam();
    }
    //methode ini digunakan untuk enampilkan salam sesuai waktu tertentu, seperti pagi, siang, dan malam.
    private void setSalam() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            tvMainSalam.setText("Selamat Pagi" + " " + " ");
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            tvMainSalam.setText("Selamat Siang" + " " + " ");
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            tvMainSalam.setText("Selamat Sore" + " " + " ");
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            tvMainSalam.setText("Selamat Malam" + " " + " ");
        }
    }
    //method ini digunakan untuk menangkapp format bulan
    private void getToday() {  //menampilkan bulan
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + year;
        tvToday.setText(formatFix);
    }

}