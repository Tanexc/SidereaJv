package ru.tanec.sidereaJv.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.items.QuestionItem;

public class TestInfoAdapter extends RecyclerView.Adapter<TestInfoAdapter.TestInfoViewHolder> {

    ArrayList<QuestionItem> data;
    int cnt = -1;
    boolean hidden = false;
    int selected;

    public TestInfoAdapter(ArrayList<QuestionItem> data, boolean timer) {
        this.data = data;
        this.hidden = timer;
        selected = 0;

    }

    @NonNull
    @Override
    public TestInfoAdapter.TestInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_test_recycler, parent, false);

        cnt++;

        return new TestInfoViewHolder(view, data.get(cnt), cnt, hidden, selected, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TestInfoViewHolder holder, int position) {
        holder.bind(data.get(position), position, selected, this);
    }

    public void setData(ArrayList<QuestionItem> data, int id) {
        this.data = data;
        cnt = -1;
        notifyItemChanged(id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void selectedItem(Integer id) {
        selected = id;
        cnt = -1;
        notifyDataSetChanged();
    }


    static class TestInfoViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        CardView card;
        Context context;
        boolean hidden;

        @SuppressLint("SetTextI18n")
        public TestInfoViewHolder(@NonNull View itemView, QuestionItem data, int cnt, boolean hidden, int selected, TestInfoAdapter adpt) {
            super(itemView);
            this.context = itemView.getContext();
            this.hidden = hidden;
            number = itemView.findViewById(R.id.question_number);
            card = itemView.findViewById(R.id.card);
            if (cnt == selected) {
                card.setScaleX((float) 0.8);
                card.setScaleY((float) 0.8);
            }
            int c = cnt;
            cnt++;
            number.setText("" + cnt);
            cnt--;

            card.setOnClickListener(l->{
                adpt.selectedItem(c);
                FragmentController.currentId.setValue(Integer.parseInt((String) number.getText()) - 1);
            });


        }

        @SuppressLint({"SetTextI18n", "UseCompatLoadingForColorStateLists"})
        public void bind(QuestionItem item, int pos, int selected, TestInfoAdapter adpt) {
            pos++;
            number.setText("" + pos);
            if (pos == selected) {
                card.setScaleX((float) 0.8);
                card.setScaleY((float) 0.8);
            } else {
                card.setScaleX((float) 1);
                card.setScaleY((float) 1);
            }
            if (!hidden) {
                if (item.checkAnswer() == Boolean.TRUE) {
                    card.setBackgroundTintList(context.getResources().getColorStateList(R.color.md_theme_dark_green));
                } else if (item.checkAnswer() == Boolean.FALSE) {
                    card.setBackgroundTintList(context.getResources().getColorStateList(R.color.md_theme_dark_onError));
                } else {
                    card.setBackgroundTintList(context.getResources().getColorStateList(R.color.md_theme_dark_surfaceVariant));
                }
            }
            int finalPos = pos;
            card.setOnClickListener(l->{
                adpt.selectedItem(finalPos);
                FragmentController.currentId.setValue(Integer.parseInt((String) number.getText()) - 1);
            });


        }

    }
}

