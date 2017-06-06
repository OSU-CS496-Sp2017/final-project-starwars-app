package com.example.cs496finalproject.finalprojectstarwars;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
/**
 * Created by pranavramesh on 6/5/17.
 */

public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.ViewHolder> {
    private List<StarWarsInfo> swList;
    private Context context;

    public StarWarsAdapter(List<StarWarsInfo> swList1, Context context) {
        this.swList = swList1;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View w = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display, parent, false);
        return new ViewHolder(w);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StarWarsInfo item = swList.get(position);

        holder.swString2.setText(item.getSWInfo());

    }

    @Override
    public int getItemCount() {
        return swList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView swString2;

        public ViewHolder(View itemView) {
            super(itemView);

            swString2 = (TextView) itemView.findViewById(R.id.swString);
        }
    }
}
