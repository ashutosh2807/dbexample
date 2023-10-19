package com.example.dbexample.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dbexample.Data.profileData;
import com.example.dbexample.R;
import com.example.dbexample.databasePackages.dbProfiles;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>  {
    private ArrayList<profileData> profileDataArrayList;
    private Activity context;
    private onItemClickListener myListener;


    public interface onItemClickListener {
        void onItemClicking(int position);
    }


    public void setOnItemClickListener(onItemClickListener listener) {
        myListener = listener;
    }

    public recyclerAdapter(ArrayList<profileData> dataArrayList, Activity context) {
        this.profileDataArrayList = (new dbProfiles(context)).getAllEmployees();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachrow, parent, false);
        return new MyViewHolder(itemView, myListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        profileData currentItem = profileDataArrayList.get(position);
        holder.hTitle.setText(currentItem.getName());
        holder.hid.setText(String.valueOf(currentItem.getProfileID()));

    }

    @Override
    public int getItemCount() {
        return profileDataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hTitle;
        TextView hid;
        TextView hinfo;

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            hTitle = itemView.findViewById(R.id.headingTitle);
            hid = itemView.findViewById(R.id.hid);
            hinfo = itemView.findViewById(R.id.hinfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicking(position);
                        }
                    }
                }
            });
        }
    }
}
