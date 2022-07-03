package irawan.electroshock.tungpat.controller;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
