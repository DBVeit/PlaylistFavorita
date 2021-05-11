package com.example.playlistfavorita.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private MusicDatabase database;
    public ArrayList<Music> musics;
    private Context context;

    public void setContext(Context context){
        this.context = context;
        database = new MusicDatabase(context);
        musics = database.retrieveMusicFromDB();
    }
    public ArrayList<Music> getMusic(){
        return musics;
    }
    public void addMusic(Music music){
        long id = database.createMusicInDB(music);
        if(id > 0){
            music.setId(id);
            musics.add(music);
            Toast.makeText(context,"Musica adicionada :)", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Erro ao adicionar musica :(", Toast.LENGTH_LONG).show();
        }
    }
    public void updateMusic(Music music, int index) {
        int count = database.updateMusicInDB(music);
        if (count > 0) {
            musics.set(index, music);
        }
    }
    public void removeMusic(int index){
        int count = database.removeMusicFromDB(
                musics.get(index)
        );
        if (count > 0){
            musics.remove(index);
        }
    }
}
