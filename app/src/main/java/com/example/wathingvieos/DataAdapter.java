package com.example.wathingvieos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<String> comentaris;
    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> comentaris) {
        this.comentaris = comentaris;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String msg = comentaris.get(position);
        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {
        return comentaris.size();
    }
}
