package com.example.mobilkita;

import android.R.layout;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobilkita.R;
import com.example.mobilkita.helper.DataHelper;

public class DaftarMobilActivity extends AppCompatActivity {  //untuk menampilkan daftar mobil
    //mendeklarasikan daftar dengan tipe data string untuk daftar, dan listview untuk listview, menu untuk menu
    String[] daftar;
    ListView ListView1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarMobilActivity m;

    @Override //anotasi override berfungsi untuk membantu memeriksa apakah harus mengganti metode pada kelas induk
    protected void onCreate(Bundle savedInstanceState) { //method ini untuk menampilkan daftar nama nama mobil yang sudah di buat di layout activity_mobil
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobil);

        m = this;
        dbcenter = new DataHelper(this); //memanggil data dari database

        RefreshList();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mobil", null); //mengambil data dari database tabel mobil
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }
        ListView1 = findViewById(R.id.listView1);
        ListView1.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, daftar));
        ListView1.setSelected(true);
        ListView1.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent i = new Intent(DaftarMobilActivity.this, DetailMobilActivity.class);
                i.putExtra("merk", selection);
                startActivity(i);
            }
        });

        ((ArrayAdapter) ListView1.getAdapter()).notifyDataSetInvalidated();

    }
}
