package org.ayo.robot.anim;

import android.app.Application;

/**
 * Created by cowthan on 2016/1/24.
 */
public class App extends Application{

    public static Application app;



    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
