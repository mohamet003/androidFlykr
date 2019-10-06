package com.example.flyker.ui.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flyker.R;
import com.example.flyker.modele.Photo;
import com.example.flyker.ui.ui.main.MainFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Photo> photos;
    private ItemClickListener listener;

    public MyAdapter(List<Photo> photos , ItemClickListener listener) {
        this.photos = photos;
        this.listener = listener;
    }


    interface ItemClickListener {
        void onClick(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private LinearLayout v;
        public MyViewHolder(LinearLayout v) {
            super(v);
            this.v = v;
            this.v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        // instancie la vue d'un item en allant chercher le layout particulier
        LinearLayout v = (LinearLayout)
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.photo, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView image = holder.v.findViewById(R.id.imagee);
        Photo photo = this.photos.get(position);
        String url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId()+"_"+photo.getSecret() + ".jpg";
/*
        MainFragment.VersFullFragment action =
                (MainFragmentDirections.VersFullFragment)
                        MainFragmentDirections.versListeFragment();
        action.setUrl(url);
        Navigation.findNavController(v).navigate(action);
*/
        Glide.with(holder.v).load(url).into(image);
    }

    @Override
    public int getItemCount() {
        return this.photos.size();
    }


}
