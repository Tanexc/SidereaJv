package ru.tanec.sidereaJv;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.tanec.sidereaJv.api.Constellation;
import ru.tanec.sidereaJv.items.ConstellationItem;
import ru.tanec.sidereaJv.items.QuestionItem;

public class ConstellationsController {
    private static List<ConstellationItem> data;
    public static MutableLiveData<List<ConstellationItem>> liveData = new MutableLiveData<>();
    public static ArrayList<QuestionItem> questions;
    public static MutableLiveData<Integer> selectedAnswer;

    public static void setData(List<Constellation> preData) {
        data = new ArrayList<>();
        for (int i = 0; i < preData.size(); i++) {
            Constellation item = preData.get(i);
            ConstellationItem it = new ConstellationItem(item);
            data.add(it);
        }
        setImages();
    }

    public static ArrayList<QuestionItem> getQuestions() {
        return questions;
    }

    public static void addDataItem(Constellation item) {
        ConstellationItem it = new ConstellationItem(item);
        data.add(it);
        setImages();
    }

    public static void setImages() {
        new Thread(() -> {
            for (int i = 0; i < data.size(); i++) {
                ConstellationItem item = data.get(i);
                if (item.getImageBitmap() == null) {
                    try {
                        item.setImage(item.getObject().getImage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    data.set(i, item);
                    new Handler(Looper.getMainLooper()).post(() -> {
                        liveData.setValue(data);
                    });
                }
            }
        }).start();

    }

    public static List<ConstellationItem> getData() {
        return data;
    }

    public static ArrayList<ConstellationItem> getDataByTitle(CharSequence title) {
        ArrayList<ConstellationItem> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String item = data.get(i).getObject().getTitle();
            if (item != null) {
                if (item.toLowerCase().contains(title)) {
                    res.add(data.get(i));
                }
            }

        }
        return res;
    }

    public static void setQuestions(ArrayList<QuestionItem> qs) {
        questions = qs;
    }

    public static ArrayList<QuestionItem> setQuestions(int id) {
        ArrayList<QuestionItem> dt = new ArrayList<>();
        QuestionItem item;
        Random random = new Random();
        ArrayList<ConstellationItem> dataRequired = new ArrayList<>();

        if (id == 0) {

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getObject().getPolusharie() == 1) {
                    dataRequired.add(data.get(i));
                }
            }

        } else if (id == 1) {

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getObject().getPolusharie() == 2) {
                    dataRequired.add(data.get(i));
                }
            }

        } else if (id == 2) {

            for (int i = 0; i < 20; i++) {
                dataRequired.add(data.get(random.nextInt(88)));
            }

        } else if (id == 3) {

            dataRequired.addAll(data);

        }

        for (int i = 0; i < dataRequired.size(); i++) {
            ArrayList<Integer> answerIds = new ArrayList<>();
            ArrayList<ConstellationItem> answers = new ArrayList<>();
            item = new QuestionItem();
            item.setId(i);
            item.setItem(dataRequired.get(i));

            answerIds.add((int) item.getItem().getObject().getId());
            answers.add(item.getItem());
            while (answers.size() != 3) {
                ConstellationItem it = data.get(random.nextInt(88));
                if (!answerIds.contains((int) it.getObject().getId())) {
                    answers.add(it);
                    answerIds.add((int) it.getObject().getId());
                }
            }

            Collections.shuffle(answers);
            item.setAnswers(answers);
            dt.add(item);

        }

        Collections.shuffle(dt);
        questions = dt;
        FragmentController.currentId.setValue(0);
        return dt;
    }
}
