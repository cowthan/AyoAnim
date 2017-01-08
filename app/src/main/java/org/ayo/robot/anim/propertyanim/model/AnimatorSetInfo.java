package org.ayo.robot.anim.propertyanim.model;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class AnimatorSetInfo  implements Serializable {

    public static final int PLAY_TOGETHER = 1;
    public static final int PLAY_SEQUENCIAL = 2;

    public int playSequencial = 1; //1-together, 2-sequencial

    public List<AnimatorInfo> animators;

    public AnimatorSet parse(View v){
        AnimatorSet as = new AnimatorSet();
        List<Animator> list = new ArrayList<>();
        for(AnimatorInfo a: animators){
            list.add(a.parse(v));
        }
        if(playSequencial == PLAY_TOGETHER){
            as.playTogether(list);
        } else if(playSequencial == PLAY_SEQUENCIAL){
            as.playSequentially(list);
        }
        return as;
    }

}
