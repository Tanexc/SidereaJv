package ru.tanec.sidereaJv.api;

public class Constellation {
    private long id;
    private String title;
    private String info;
    private String image;
    private String declination;
    private String ascent;
    private Integer polusharie;
    private String lat;
    private String alpha;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setImage(String image) { this.image = image; }

    public void setInfo(String info) { this.info = info; }

    public void setAscent(String ascent) {
        this.ascent = ascent;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }

    public void setPolusharie(Integer polusharie) {
        this.polusharie = polusharie;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getImage() {
        return this.image;
    }

    public String getInfo() {
        return this.info;
    }

    public String getAscent() {
        return this.ascent;
    }

    public String getDeclination() {
        return this.declination;
    }

    public Integer getPolusharie() {
        return this.polusharie;
    }

    public String getLat() {
        return this.lat;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
