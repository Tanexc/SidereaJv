package ru.tanec.sidereaJv.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.items.ConstellationItem;
import ru.tanec.sidereaJv.items.QuestionItem;


public class TestFragment extends Fragment {
    Toolbar toolbar;
    CardView info;
    CardView north;
    CardView south;
    CardView random;
    CardView all;


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
            FragmentController.setCurrentFragment(R.id.contest, true);
        });
        south.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(1);
            FragmentController.setCurrentFragment(R.id.contest, true);
        });
        all.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(3);
            FragmentController.setCurrentFragment(R.id.contest, true);
        });
        random.setOnClickListener(l->{
            ArrayList<QuestionItem> questions = new ArrayList<>();
            questions = ConstellationsController.setQuestions(2);
            FragmentController.setCurrentFragment(R.id.contest, true);
        });
    }
}