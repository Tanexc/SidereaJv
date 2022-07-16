package ru.tanec.sidereaJv.items;

public class CardItem {
    int id;
    int resouceId;
    String title;

    public CardItem(int resouceId,
                    String title) {
        this.resouceId = resouceId;
        this.title = title;
    }

    public CardItem() {}

    public CardItem(String title) {
        this.title = title;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResouceId() {
        return resouceId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
