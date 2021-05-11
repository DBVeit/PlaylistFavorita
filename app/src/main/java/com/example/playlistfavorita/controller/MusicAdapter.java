package com.example.playlistfavorita.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playlistfavorita.R;
import com.example.playlistfavorita.model.DataModel;
import com.example.playlistfavorita.model.Music;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private OnItemClickListener clickListener;

    interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_music_recyclerview,
                        parent,false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
        Music m = DataModel.getInstance().getMusic().get(position);
        holder.txtVtitle.setText(m.getTitle());
        holder.txtVArtist.setText(m.getArtist());
        holder.txtVAlbum.setText(m.getAlbum());
        holder.txtVYear.setText(String.valueOf(m.getYear()));
        holder.txtVGenre.setText(m.getGenre());
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getMusic().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtVtitle;
        TextView txtVArtist;
        TextView txtVAlbum;
        TextView txtVYear;
        TextView txtVGenre;

        public ViewHolder(View itemView){
            super(itemView);
            txtVtitle = itemView.findViewById(R.id.txtVTitle);
            txtVArtist = itemView.findViewById(R.id.txtVArtist);
            txtVAlbum = itemView.findViewById(R.id.txtVAlbum);
            txtVYear = itemView.findViewById(R.id.txtVYear);
            txtVGenre = itemView.findViewById(R.id.txtVGenre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener != null){
                        clickListener.onItemClick(view,getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    if(clickListener != null){
                        clickListener.onItemLongClick(view,getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
