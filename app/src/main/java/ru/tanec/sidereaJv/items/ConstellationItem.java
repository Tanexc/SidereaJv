package ru.tanec.sidereaJv.items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ru.tanec.sidereaJv.api.Constellation;

public class ConstellationItem {
    Constellation object;
    Bitmap imageBitmap;


    public ConstellationItem(Constellation object) {
        super();
        this.object = object;
    }

    public void setImage(String image) throws IOException {
        URL url = new URL(image);
        imageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

    public void setObject(Constellation object) {
        this.object = object;
    }

    public Constellation getObject() {
        return object;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}
