package ru.tanec.sidereaJv.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tanec.sidereaJv.ConstellationsController;
import ru.tanec.sidereaJv.FragmentController;
import ru.tanec.sidereaJv.R;
import ru.tanec.sidereaJv.api.Api;
import ru.tanec.sidereaJv.api.Constellation;
import ru.tanec.sidereaJv.api.ConstellationsApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentController.setActivity(this);

        List<Constellation> data = new ArrayList<>();
        new Thread(() -> {
            List<Constellation> dt = new ArrayList<>();

            try {
                dt = Api.getApi().getAll().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (dt == null) {
                dt = new ArrayList<>();
            }

            List<Constellation> finalDt = dt;
            new Handler(Looper.getMainLooper()).post(()->{
                ConstellationsController.setData(finalDt);
            });

        }).start();

    }
}