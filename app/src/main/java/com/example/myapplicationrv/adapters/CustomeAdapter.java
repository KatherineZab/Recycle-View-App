package com.example.myapplicationrv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.models.Data;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {

    private ArrayList<Data> arr;

    public CustomeAdapter(ArrayList<Data> arr) {
        this.arr = new ArrayList<>(arr); // יצירת עותק כדי למנוע בעיות בעדכון רשימה
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView nameVersion;
        ImageView imageViewItem;

        public myViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.textName);
            nameVersion = itemView.findViewById(R.id.textVer);
            imageViewItem = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.myViewHolder holder, int position) {
        Data item = arr.get(position);
        holder.username.setText(item.getName());
        holder.nameVersion.setText(item.getVersion());
        holder.imageViewItem.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    // פונקציה לעדכון הרשימה בסינון
    public void updateList(ArrayList<Data> newList) {
        arr.clear();
        arr.addAll(newList);
        notifyDataSetChanged(); // עדכון התצוגה
    }
}
