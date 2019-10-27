package com.msmi.sqlitemovies.activity;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.msmi.sqlitemovies.R;
import com.msmi.sqlitemovies.adapter.MoviesAdapter;
import com.msmi.sqlitemovies.koneksi.KoneksiDatabase;
import com.msmi.sqlitemovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class LihatDataActivity extends AppCompatActivity {
    KoneksiDatabase moviedb;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        moviedb = new KoneksiDatabase(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MoviesAdapter(movieList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    private void prepareMovieData() {
        Cursor res = moviedb.TampilData();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                String id = res.getString(0);
                String title = res.getString(1);
                String genre = res.getString(2);
                String years = res.getString(3);
                Movie movie = new Movie(id,title, genre, years);
                movieList.add(movie);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
