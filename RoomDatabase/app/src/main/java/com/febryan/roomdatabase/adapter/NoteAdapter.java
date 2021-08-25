package com.febryan.roomdatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.febryan.roomdatabase.R;
import com.febryan.roomdatabase.activity.UpdateActivity;
import com.febryan.roomdatabase.room.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    Context context;
    private  final  DeleteItem deleteItem;
    List<Note> list;

    public NoteAdapter(Context context, List<Note> list, DeleteItem deleteItem){
        this.context = context;
        this.list = list;
        this.deleteItem = deleteItem;
    }

    //Call Layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDesc());

        Context context = holder.itemView.getContext();
        holder.update.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(list.get(position).getKey()));
            intent.putExtra("title", String.valueOf(list.get(position).getTitle()));
            intent.putExtra("desc", String.valueOf(list.get(position).getDesc()));
            context.startActivity(intent);
        });

        holder.delete.setOnClickListener(v -> {
            deleteItem.onItemDelete(position, list.get(position).getKey());
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        Button update, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_update_title);
            desc = itemView.findViewById(R.id.tv_update_desc);
            update = itemView.findViewById(R.id.updateId);
            delete = itemView.findViewById(R.id.deleteId);

        }
    }

    public interface DeleteItem{
        void onItemDelete(int position, int id);
    }

}
