package com.andriod.asifnewaz.oceanologicaldictionary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Model> dataSet;
    private Context context ;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.word= (TextView)itemView.findViewById(R.id.wordtext);
        }
    }

    public CustomAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Details
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Word", dataSet.get(myViewHolder.getAdapterPosition()).getWord());
                intent.putExtra("Detail", dataSet.get(myViewHolder.getAdapterPosition()).getDefinition());
                intent.putExtra("Model",dataSet.get(myViewHolder.getAdapterPosition()));
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView word1= holder.word;

        word1.setText(dataSet.get(listPosition).getWord());
//        meaning1.setText(dataSet.get(listPosition).getMeaning());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}