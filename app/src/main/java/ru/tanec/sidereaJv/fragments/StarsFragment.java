package ru.tanec.sidereaJv.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.adapters.RecyclerCardAdapter;
import ru.tanec.sidereaJv.api.Constellation;
import ru.tanec.sidereaJv.items.ConstellationItem;


public class StarsFragment extends Fragment {
    RecyclerView recycler;
    RecyclerCardAdapter adapter;
    List<ConstellationItem> adapterData;
    Toolbar toolbar;
    TextInputEditText textInput;
    ProgressBar bar;

    public StarsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInput = view.findViewById(R.id.text_input);
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.stars_recycler);
        adapterData = ConstellationsController.getData();
        bar = view.findViewById(R.id.progressBar);
        if (adapterData == null) {
            adapterData = new ArrayList<>();
        }
        adapter = new RecyclerCardAdapter(adapterData);

        toolbar.setNavigationOnClickListener(l -> {
            if (textInput.getVisibility() == View.GONE) {
                toolbar.setNavigationIcon(R.drawable.ic_outline_close_24);
                textInput.setVisibility(View.VISIBLE);
            } else {
                toolbar.setNavigationIcon(R.drawable.ic_baseline_search_24);
                textInput.setVisibility(View.GONE);
                ConstellationsController.liveData.setValue(ConstellationsController.getData());
             }
          });

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<ConstellationItem> a = ConstellationsController.getDataByTitle(s);
                ConstellationsController.liveData.setValue(a);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ConstellationsController.liveData.observe(requireActivity(), constellationItems -> {
            adapterData = constellationItems;
            if (constellationItems != null) {
                bar.setVisibility(View.GONE);
                adapter.setList(adapterData);
            }
        });

        recycler.setAdapter(adapter);
    }
}