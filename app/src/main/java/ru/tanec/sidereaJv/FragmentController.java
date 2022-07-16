package ru.tanec.sidereaJv;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.HttpCookie;
import java.util.ArrayList;

import ru.tanec.sidereaJv.items.ConstellationItem;
import ru.tanec.sidereaJv.items.QuestionItem;

public class FragmentController {
    public static MutableLiveData<Integer> currentId = new MutableLiveData<>();
    private static int lastFragmentId;
    private static AppCompatActivity activity;
    private static BottomNavigationView navigation;
    private static NavController navController;
    private static ConstellationItem item;
    private static boolean timer;

    public static void setActivity(AppCompatActivity activity) {
        FragmentController.activity = activity;
        navigation = activity.findViewById(R.id.bottomNavigationView);
        navigation.setSelectedItemId(R.id.stars);
        navController = Navigation.findNavController(activity, R.id.nav_host);
        navigation.setOnItemSelectedListener(item -> {
            navController.popBackStack();
            navController.navigate(item.getItemId());
            return true;
        });
    }

    public static void setCurrentFragment(int id, ConstellationItem it) {
        item = it;
        lastFragmentId = navController.getCurrentDestination().getId();
        navController.navigate(id);
        navigation.setVisibility(View.GONE);
    }

    public static void setCurrentFragment(int id, int previousFragmentId) {
        lastFragmentId = previousFragmentId;
        navController.navigate(id);
        navigation.setVisibility(View.GONE);
    }

    public static void setCurrentFragment(int id, int previousFragmentId, int navState) {
        lastFragmentId = previousFragmentId;
        navController.navigate(id);
        navigation.setVisibility(navState);
    }

    public static void setCurrentFragment(int id, boolean t) {
        timer = t;
        lastFragmentId = navController.getCurrentDestination().getId();
        navController.navigate(id);
        navigation.setVisibility(View.GONE);
    }

    public static void setCurrentFragment(int id) {
        lastFragmentId = navController.getCurrentDestination().getId();
        navController.navigate(id);
        navigation.setVisibility(View.GONE);
    }

    public static void closeCurrentFragment() {
        navigation.setVisibility(View.VISIBLE);
        navController.navigate(lastFragmentId);
    }

    public static ConstellationItem getItem() {
        return item;
    }

    public static boolean getTimer() {
        return timer;
    }
}
