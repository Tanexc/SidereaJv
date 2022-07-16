package ru.tanec.sidereaJv.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.fragments.ConViewFragment;
import ru.tanec.sidereaJv.items.ConstellationItem;

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.CardViewHolder> {
    List<ConstellationItem> data;
    int cnt = -1;

    public RecyclerCardAdapter(List<ConstellationItem> list) {
        data = list;

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_stars, parent, false);

        cnt++;

        return new CardViewHolder(view, data.get(cnt));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setList(List<ConstellationItem> adapterData) {
        data = adapterData;
        cnt = -1;
        notifyDataSetChanged();

    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView card;
        ConstellationItem item;
        ProgressBar progressBar;

        public CardViewHolder(@NonNull View itemView, ConstellationItem it) {
            super(itemView);
            image = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_text);
            card = itemView.findViewById(R.id.item_card_view);
            progressBar = itemView.findViewById(R.id.progressBar);
            bind(it);

        }

        public void bind(ConstellationItem it) {
            item = it;
            title.setText(it.getObject().getTitle());

            Bitmap bitmap = it.getImageBitmap();
            if (bitmap != null) {
                progressBar.setVisibility(View.GONE);
                image.setImageBitmap(it.getImageBitmap());
            }
            card.setOnClickListener(v -> {

                FragmentController.setCurrentFragment(R.id.constellation_fragment, it);

            });
        }
    }
}
