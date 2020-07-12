package com.example.loginform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginform.R;
import com.example.loginform.model.SampleModel;

import java.util.ArrayList;

public class AdapterShowResult extends RecyclerView.Adapter<AdapterShowResult.ViewHolder> {
   private Context mContext;
   private ArrayList<SampleModel> mSampleModel;

    public AdapterShowResult(Context mContext, ArrayList<SampleModel> mSampleModel) {
        this.mContext = mContext;
        this.mSampleModel = mSampleModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_showdetails, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SampleModel sampleModel = mSampleModel.get(position);
        holder.id_username_recycle.setText(sampleModel.getUsername());
        holder.id_password_recycle.setText(sampleModel.getUsername());
        holder.id_nickname_recycle.setText(sampleModel.getUsername());
       }

    @Override
    public int getItemCount() {
        return mSampleModel.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_username_recycle,id_password_recycle,id_nickname_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_username_recycle = itemView.findViewById(R.id.id_username_recycle);
            id_password_recycle = itemView.findViewById(R.id.id_password_recycle);
            id_nickname_recycle = itemView.findViewById(R.id.id_nickname_recycle);
        }
    }
}