package ru.tanec.sidereaJv.items;

import java.util.ArrayList;

public class QuestionItem {
    private ConstellationItem item;
    private int id;
    private ArrayList<ConstellationItem> answers;
    private ConstellationItem selected;

    public int getId() {
        return id;
    }

    public ConstellationItem getItem() {
        return item;
    }

    public ArrayList<ConstellationItem> getAnswers() {
        return answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(ConstellationItem item) {
        this.item = item;
    }

    public void setAnswers(ArrayList<ConstellationItem> answers) {
        this.answers = answers;
    }

    public Boolean checkAnswer() {
        if (selected == null) {
            return null;
        }
        return this.selected.getObject().getId() == item.getObject().getId();
    }

    public void setSelected(ConstellationItem constellationItem) {
        selected = constellationItem;
    }

    public ConstellationItem getSelected() {
        return selected;
    }
}
