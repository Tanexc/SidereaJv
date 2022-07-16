package ru.tanec.sidereaJv.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.items.ConstellationItem;


public class ConViewFragment extends Fragment {
    ConstellationItem item;
    Toolbar toolbar;
    TextView info;
    ImageView image;
    TextView declination;
    TextView ascent;
    TextView hemisphere;
    TextView lat;
    TextView title;
    TextView alpha;

    public ConViewFragment() {
        item = FragmentController.getItem();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_con_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(l-> FragmentController.closeCurrentFragment());
        image = view.findViewById(R.id.image);
        info = view.findViewById(R.id.info);
        declination = view.findViewById(R.id.declination_value);
        ascent = view.findViewById(R.id.ascent_value);
        hemisphere = view.findViewById(R.id.polusharie_value);
        title = view.findViewById(R.id.title);
        alpha = view.findViewById(R.id.alpha_value);

        image.setImageBitmap(item.getImageBitmap());
        info.setText(item.getObject().getInfo());
        declination.setText(item.getObject().getDeclination());
        ascent.setText(item.getObject().getAscent());
        alpha.setText(item.getObject().getAlpha());
        title.setText(item.getObject().getTitle() + " " + item.getObject().getLat());
        Integer hs = item.getObject().getPolusharie();
        if (hs == 1) {
            hemisphere.setText("Северное");
        } else if (hs == 2) {
            hemisphere.setText("Южное");
        } else {
            hemisphere.setText("Экваториальное");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentController.closeCurrentFragment();
    }
}