package org.ayo.sample.menu;

import android.app.Activity;

import org.ayo.core.attacher.ActivityAttacher;

import java.io.Serializable;

/**
 * 最底级菜单，必须直接跳到一个fragment，并带有一个说明页，对应assets中的一个html
 * Created by cowthan on 2016/1/17.
 */
public class Leaf implements Serializable{
    public String name;
    public String htmlAssetsPath;
    public Class<? extends ActivityAttacher> attacherClass;
    public Class<? extends Activity> activityClass;

    public Leaf(String name, String htmlAssetsPath, Class<? extends ActivityAttacher> attacherClass){
        this.name = name;
        this.htmlAssetsPath = htmlAssetsPath;
        this.attacherClass = attacherClass;

    }

    public Leaf(String name, String htmlAssetsPath, Class<? extends Activity> activityClass, int x){
        this.name = name;
        this.htmlAssetsPath = htmlAssetsPath;
        this.activityClass = activityClass;
    }
}


