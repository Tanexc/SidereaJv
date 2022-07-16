package ru.tanec.sidereaJv.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.adapters.TestInfoAdapter;
import ru.tanec.sidereaJv.items.ConstellationItem;
import ru.tanec.sidereaJv.items.QuestionItem;

public class ContestFragment extends Fragment {

    RecyclerView recycler;
    TestInfoAdapter adapter;
    ImageView image;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    ArrayList<QuestionItem> questions;
    Integer id;
    Button send;
    ImageView prev;
    ImageView next;
    boolean timer;
    Chronometer chronometer;
    Toolbar toolbar;

    public ContestFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contest_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = 0;
        timer = FragmentController.getTimer();

        questions = ConstellationsController.getQuestions();

        image = view.findViewById(R.id.image);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        chronometer = view.findViewById(R.id.chronometer);
        toolbar = view.findViewById(R.id.toolbar);

        recycler = view.findViewById(R.id.test_recycler);

        send = view.findViewById(R.id.end_btn);
        next = view.findViewById(R.id.next_btn);
        prev = view.findViewById(R.id.prev_btn);

        next.setOnClickListener(l->{
            if (id < questions.size() - 1) {
                bind(id + 1);
            } else {
                bind(0);
            }
            recycler.scrollToPosition(id);
            adapter.selectedItem(id + 1);
        });
        prev.setOnClickListener(l->{
            if (id > 0) {
                bind(id - 1);
            } else {
                bind(questions.size() - 1);
            }
            recycler.scrollToPosition(id);
            adapter.selectedItem(id + 1);
        });

        send.setOnClickListener(l->{
            ConstellationsController.setQuestions(questions);
            FragmentController.setCurrentFragment(R.id.result, R.id.test);
        });
        adapter = new TestInfoAdapter(questions, timer);

        if (timer) {
            toolbar.setNavigationOnClickListener(l->{
                FragmentController.setCurrentFragment(R.id.test, R.id.stars, View.VISIBLE);
            });
            chronometer.setCountDown(true);
            chronometer.setBase(SystemClock.elapsedRealtime() + 1000 * 60 * 10);
            chronometer.setOnChronometerTickListener(l->{
                if (chronometer.getBase() == 0) {
                    ConstellationsController.setQuestions(questions);
                    FragmentController.setCurrentFragment(R.id.result, R.id.test);
                }
            });
        } else {
            toolbar.setNavigationOnClickListener(l->{
                FragmentController.setCurrentFragment(R.id.learn, R.id.stars, View.VISIBLE);
            });
        }

        recycler.setAdapter(adapter);

        bind(1);

        FragmentController.currentId.observe(requireActivity(), this::bind);

        chronometer.start();

    }

    public void bind(Integer id) {
        QuestionItem item = questions.get(id);
        this.id = id;
        image.setImageBitmap(item.getItem().getImageBitmap());
        answer1.setText(item.getAnswers().get(0).getObject().getTitle());
        answer2.setText(item.getAnswers().get(1).getObject().getTitle());
        answer3.setText(item.getAnswers().get(2).getObject().getTitle());

        answer3.setChecked(Boolean.FALSE);
        answer2.setChecked(Boolean.FALSE);
        answer1.setChecked(Boolean.FALSE);

        for (int i = 0;i<3;i++) {
            if (item.getSelected() == null) {
                break;
            }
            if (item.getSelected().getObject().getId() == item.getAnswers().get(i).getObject().getId()) {
                if (i == 0) {
                    answer1.setChecked(true);
                } else if (i == 1) {
                    answer2.setChecked(true);
                } else {
                    answer3.setChecked(true);
                }
            }
        }

        answer1.setOnClickListener(l->{
            item.setSelected(item.getAnswers().get(0));
            questions.set(id, item);
            adapter.setData(questions, id);
            answer1.setChecked(Boolean.TRUE);
            answer3.setChecked(Boolean.FALSE);
            answer2.setChecked(Boolean.FALSE);
        });

        answer2.setOnClickListener(l->{
            item.setSelected(item.getAnswers().get(1));
            questions.set(id, item);
            adapter.setData(questions, id);
            answer2.setChecked(Boolean.TRUE);
            answer3.setChecked(Boolean.FALSE);
            answer1.setChecked(Boolean.FALSE);
        });

        answer3.setOnClickListener(l->{
            item.setSelected(item.getAnswers().get(2));
            questions.set(id, item);
            adapter.setData(questions, id);
            answer3.setChecked(Boolean.TRUE);
            answer1.setChecked(Boolean.FALSE);
            answer2.setChecked(Boolean.FALSE);
        });
    }
}
