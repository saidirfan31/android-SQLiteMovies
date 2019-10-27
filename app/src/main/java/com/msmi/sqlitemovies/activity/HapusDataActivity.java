package com.msmi.sqlitemovies.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msmi.sqlitemovies.R;
import com.msmi.sqlitemovies.koneksi.KoneksiDatabase;

public class HapusDataActivity extends AppCompatActivity {
    EditText Id;
    Button hapus;
    KoneksiDatabase moviedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_data);
        Id = (EditText) findViewById(R.id.mId);
        hapus = (Button) findViewById(R.id.hapus);
        moviedb = new KoneksiDatabase(this);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusData();
            }
        });
    }

    public void hapusData() {
        String id = Id.getText().toString();
        int hasil = moviedb.DeleteData(id);
        Toast.makeText(this, "Databerhasil Di hapus", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(HapusDataActivity.this, LihatDataActivity.class);
        startActivity(i);
    }
}