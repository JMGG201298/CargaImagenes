package com.example.cargaimagenes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Heroe> lista;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecyclerViewAdapter(List<Heroe> lista, Context context){
        this.lista=lista;
        this.layoutInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return  lista.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_heroe, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.bindData(lista.get(position),context);
    }
    public void setItems(List<Heroe> lista){
        this.lista=lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView iVImagen;
        TextView txtNombre;
        ViewHolder(View itemView){
            super(itemView);
            iVImagen=(NetworkImageView) itemView.findViewById(R.id.iVHeroe);
            txtNombre=itemView.findViewById(R.id.txtNombre);
        }

        void bindData(final Heroe heroe, Context context){
            iVImagen.setImageUrl(heroe.getFoto(),VolleySingleton.getInstance(context).getImageLoader());
            txtNombre.setText(heroe.getNombre());
        }
    }
}
