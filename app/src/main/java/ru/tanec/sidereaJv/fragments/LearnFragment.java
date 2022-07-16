package ru.tanec.sidereaJv.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.items.QuestionItem;


public class LearnFragment extends Fragment {
    Toolbar toolbar;
    CardView info;
    CardView north;
    CardView south;
    CardView random;
    CardView all;
    TextView infoText;

    public LearnFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.type_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.toolbar);
        info = view.findViewById(R.id.card_info);
        north = view.findViewById(R.id.north);
        south = view.findViewById(R.id.south);
        all = view.findViewById(R.id.all);
        random = view.findViewById(R.id.random);
        infoText = view.findViewById(R.id.info_text);

        infoText.setText(R.string.learn_text);

        toolbar.setNavigationOnClickListener(l->{
            if (info.getVisibility() == View.GONE) {
                toolbar.setNavigationIcon(R.drawable.ic_baseline_info_24);
                info.setVisibility(View.VISIBLE);
            } else {
                toolbar.setNavigationIcon(R.drawable.ic_outline_info_24);
                info.setVisibility(View.GONE);
            }
        });

        north.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(0);
            FragmentController.setCurrentFragment(R.id.contest, false);
        });
        south.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(1);
            FragmentController.setCurrentFragment(R.id.contest, false);
        });
        all.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(3);
            FragmentController.setCurrentFragment(R.id.contest, false);
        });
        random.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(2);
            FragmentController.setCurrentFragment(R.id.contest, false);
        });

    }
}