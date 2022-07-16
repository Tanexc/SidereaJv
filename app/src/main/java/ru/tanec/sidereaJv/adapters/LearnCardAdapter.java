package ru.tanec.sidereaJv.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnCardAdapter extends RecyclerView.Adapter<LearnCardAdapter.LearnViewHolder>{

    @NonNull
    @Override
    public LearnCardAdapter.LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnCardAdapter.LearnViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class LearnViewHolder extends RecyclerView.ViewHolder {
        ImageView image;


        public LearnViewHolder(@NonNull View itemView) {
            super(itemView);

        }


    }
}
