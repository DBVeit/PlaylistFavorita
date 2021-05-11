package com.example.playlistfavorita.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.playlistfavorita.R;
import com.example.playlistfavorita.model.DataModel;
import com.example.playlistfavorita.model.Music;

public class DetailsActivity extends AppCompatActivity {

    EditText edTxtTitle;
    EditText edTxtArtist;
    EditText edTxtAlbum;
    EditText edTxtYear;
    EditText edTxtGenre;
    Button btnInsert;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        edTxtTitle = findViewById(R.id.edTxtTitle);
        edTxtArtist = findViewById(R.id.edTxtArtist);
        edTxtAlbum = findViewById(R.id.edTxtAlbum);
        edTxtYear = findViewById(R.id.edTxtYear);
        edTxtGenre = findViewById(R.id.edTxtGenre);
        btnInsert = findViewById(R.id.btnInsert);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        index = extras.getInt("index");
        if(index >= 0){
            Music m = DataModel.getInstance().musics.get(index);
            edTxtTitle.setText(m.getTitle());
            edTxtArtist.setText(m.getArtist());
            edTxtAlbum.setText(m.getAlbum());
            edTxtYear.setText(String.valueOf(m.getYear()));
            edTxtGenre.setText(m.getGenre());
        }
    }

    public void insertBtn(View view){
        String title = edTxtTitle.getText().toString();
        String artist = edTxtArtist.getText().toString();
        String album = edTxtAlbum.getText().toString();
        String year = edTxtYear.getText().toString();
        String genre = edTxtGenre.getText().toString();
        if (index >= 0){
            //DataModel.getInstance().musics.set(index, new Music(title, artist, album, year, genre));
            DataModel.getInstance().updateMusic(new Music(title, artist, album, year, genre), index);
        }else {
            Music music = new Music(title,artist,album,year,genre);
            DataModel.getInstance().addMusic(music);
        }
        //Volta a tela inicial
        Intent intent = new Intent(
                DetailsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}