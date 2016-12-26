package org.ayo.sample.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 1级
 * Created by cowthan on 2016/1/17.
 */
public class Menu implements Serializable{

    public String name;
    public List<MenuItem> subMenus = new ArrayList<MenuItem>();
    public int iconNormal;
    public int iconPressed;

    public Menu(String name, int iconNormal, int iconPressed){
        this.name = name;
        this.iconNormal = iconNormal;
        this.iconPressed = iconPressed;
    }

    public void addMenuItem(MenuItem menuItem){
        this.subMenus.add(menuItem);
    }

}
