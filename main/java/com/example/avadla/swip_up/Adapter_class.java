package com.example.avadla.swip_up;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by avadla on 12/21/2017.
 */

public class Adapter_class extends RecyclerView.Adapter<Adapter_class.MyViewHolder> {

    Context context;
    String names[];
    public Adapter_class(Context context, String[] names) {
        this.context = context;
        this.names = names;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(names[position]);
    }


    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.profile_name);
            image = itemView.findViewById(R.id.profile);
        }
    }
}
