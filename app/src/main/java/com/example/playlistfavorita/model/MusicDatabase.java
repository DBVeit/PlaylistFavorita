package com.example.playlistfavorita.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MusicDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "my_playlist.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Music";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_ARTIST = "artist";
    private static final String COL_ALBUM = "album";
    private static final String COL_YEAR = "year";
    private static final String COL_GENRE = "genre";

    //private Context context;

    public MusicDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        //this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "+DB_TABLE+" ("+ 
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COL_TITLE + " TEXT, "+
                    COL_ARTIST + " TEXT, "+
                    COL_ALBUM + " TEXT, "+
                    COL_YEAR + " INTEGER, "+
                    COL_GENRE + " TEXT);";
        db.execSQL(query);
    }
    //Cria banco pela primeira vez

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    //Atualiza banco em relacao a estrutura
    //db = objeto parametro -> instancia classe SQLiteDatabase


    //CREATE
    public long createMusicInDB(Music music){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,music.getTitle());
        values.put(COL_ARTIST,music.getArtist());
        values.put(COL_ALBUM,music.getAlbum());
        values.put(COL_YEAR,music.getYear());
        values.put(COL_GENRE,music.getGenre());
        long id = db.insert(DB_TABLE,null,values);
        db.close();
        return id;
    }

    //READ
    public ArrayList<Music> retrieveMusicFromDB(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE,null,null,
                null,null,null,null);
        ArrayList<Music> musics = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
                String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(COL_ARTIST));
                String album = cursor.getString(cursor.getColumnIndex(COL_ALBUM));
                String year = cursor.getString(cursor.getColumnIndex(COL_YEAR));
                String genre = cursor.getString(cursor.getColumnIndex(COL_GENRE));

                Music m = new Music(id,title,artist,album,year,genre);
                musics.add(m);

            }while(cursor.moveToNext());
        }
        db.close();
        return musics;
    }

    //UPDATE
    public int updateMusicInDB(Music music){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();//Escrever no bd
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,music.getTitle());
        values.put(COL_ARTIST,music.getAlbum());
        values.put(COL_YEAR,music.getYear());
        values.put(COL_GENRE,music.getGenre());
        String id = String.valueOf(music.getId());
        int count = sqLiteDatabase.update(DB_TABLE,values,COL_ID + "=?",new String[]{id});
        sqLiteDatabase.close();
        return count;
    }

    //DELETE
    public int removeMusicFromDB(Music music){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String id = String.valueOf(music.getId());
        int count = sqLiteDatabase.delete(DB_TABLE, COL_ID + "=?", new String[]{id});
        sqLiteDatabase.close();
        return count;
    }
}
