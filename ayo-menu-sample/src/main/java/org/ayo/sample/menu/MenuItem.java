package org.ayo.sample.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 2级
 * Created by cowthan on 2016/1/17.
 */
public class MenuItem implements Serializable{

    public String name;
    public List<Leaf> subMenus = new ArrayList<Leaf>();
    public int iconNormal;
    public int iconPressed;

    public MenuItem(String name, int iconNormal, int iconPressed){
        this.name = name;
        this.iconNormal = iconNormal;
        this.iconPressed = iconPressed;
    }

    public void addLeaf(Leaf leaf){
        this.subMenus.add(leaf);
    }

}
