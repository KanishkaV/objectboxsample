package com.example.objectboxsample;



import com.example.objectboxsample.models.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjecboxSampleApp extends android.app.Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore= MyObjectBox.builder().androidContext(ObjecboxSampleApp.this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
