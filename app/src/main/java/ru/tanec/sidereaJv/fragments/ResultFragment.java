package ru.tanec.sidereaJv.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.adapters.TestInfoAdapter;
import ru.tanec.sidereaJv.items.QuestionItem;

public class ResultFragment extends Fragment {

    ImageView image;
    TextView trueAnswer;
    TextView answer;
    int id = 0;
    ArrayList<QuestionItem> questions;
    TestInfoAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

    public ResultFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = 0;

        questions = ConstellationsController.getQuestions();
        recyclerView = view.findViewById(R.id.test_recycler);
        adapter = new TestInfoAdapter(questions, false);
        recyclerView.setAdapter(adapter);
        image = view.findViewById(R.id.image);
        answer = view.findViewById(R.id.answer);
        trueAnswer = view.findViewById(R.id.true_answer);
        toolbar = view.findViewById(R.id.toolbar);

        bind(id);

        FragmentController.currentId.observe(requireActivity(), this::bind);

        toolbar.setNavigationOnClickListener(l->{
            FragmentController.setCurrentFragment(R.id.test, R.id.stars, View.VISIBLE);
        });

    }

    public void bind(int i) {
        id = i;
        image.setImageBitmap(questions.get(i).getItem().getImageBitmap());
        if (questions.get(i).getSelected() != null) {
            answer.setText(questions.get(i).getSelected().getObject().getTitle());
        } else {
            answer.setText("Ответ не дан");
        }
        trueAnswer.setText(questions.get(i).getItem().getObject().getTitle());
    }
}