package org.ayo.core.attacher;

import android.util.SparseArray;

/**
 */
public class AttacherManager {

    private AttacherManager(){
        attachers = new SparseArray<ActivityAttacher>();
    }

    private static AttacherManager instance;

    public static AttacherManager getDefault(){
        if(instance == null){
            instance = new AttacherManager();
        }
        return instance;
    }

    private SparseArray<ActivityAttacher> attachers;

    public int addAttacher(ActivityAttacher attacher){
        int id = new BundleIdGenerator().getNext();
        attachers.put(id, attacher);
        return id;
    }

    public void removeAttacher(int id){
        attachers.remove(id);
    }

    public ActivityAttacher getAttacher(int id){
        return attachers.get(id);
    }

    public boolean hasAttacher(int id){
        return attachers.indexOfKey(id) != -1;
    }

}
