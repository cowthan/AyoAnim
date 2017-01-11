package org.ayo.animate;

import android.app.Application;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class AyoAnim {
    private static Application app;

    public static Application app(){
        return app;
    }

    public static void init(Application app){
        AyoAnim.app = app;
    }
}
