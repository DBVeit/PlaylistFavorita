package com.example.playlistfavorita.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playlistfavorita.R;
import com.example.playlistfavorita.model.DataModel;
import com.example.playlistfavorita.model.Music;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MusicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        DataModel.getInstance().setContext(MainActivity.this);
        adapter = new MusicAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this)
        );

        //Evento p/ capturar o item clicado passado pelo adapter
        adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {
                goToAddActivity(index);
            }

            public void onItemLongClick(View view, int index){
                DataModel.getInstance().removeMusic(index);
                adapter.notifyItemRemoved(index);
            }
        });
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }*/

    //Add new
    public void btnAddClicked(View view) {
        goToAddActivity(-1);
    }
    void goToAddActivity(int index){
        Intent intent = new Intent(
                MainActivity.this, DetailsActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }

}