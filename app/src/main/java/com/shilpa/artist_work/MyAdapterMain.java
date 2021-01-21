//package com.shilpa.artist_work;
//
//import android.graphics.ColorSpace;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.facebook.appevents.ml.Model;
//
//import java.util.ArrayList;
//
//public class MyAdapterMain extends RecyclerView.Adapter<MyViewHolder> {
//
//    ArrayList<ModelMain> data;
//
//    public MyAdapterMain(ArrayList<ModelMain> data) {
//        this.data = data;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
//        View view= inflater.inflate(R.layout.singlerow,parent,false);
//        return new MyViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
//    {
//        holder.text1.setText(data.get(position).getHeader());
//        holder.text2.setText(data.get(position).getDosc());
//        holder.imageView.setImageResource(data.get(position).getImgname());
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//}
