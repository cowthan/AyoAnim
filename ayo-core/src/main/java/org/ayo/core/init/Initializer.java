package org.ayo.core.init;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 */
public class Initializer {

    /**
     */
    public interface Step{

        boolean doSeriousWork();

        String getName();

        String getNotify();

        boolean acceptFail();

    }

    public interface StepListner{
        /**
         * @param step  current step
         * @param isSuccess  if is ok
         * @param currentStep from 1
         * @param total  total
         * @return true means next step can be done, false means next step isn't necessary any more
         */
        boolean onSuffering(Step step, boolean isSuccess, int currentStep, int total);
    }

    private List<Step> steps = new ArrayList<Step>();
    private StepListner stepListner;

    private Initializer(){

    }

    public static Initializer initailizer(){
        return new Initializer();
    }

    public Initializer addStep(Step step){
        steps.add(step);
        return this;
    }

    public Initializer setStepListener(StepListner stepListener){
        this.stepListner = stepListener;
        return this;
    }

    /**
     */
    public void suffer(){
        int total = steps.size();
        if(total == 0){
            if(this.stepListner != null) this.stepListner.onSuffering(null, true, 0, 0);
            return;
        }

        int current = 0;
        for(Step step: steps){
            current += 1;
            Log.i("initialize", "init--" + step.getName());
            boolean isSuccess = step.doSeriousWork();
            Log.i("initialize", "init--" + isSuccess);
            if(this.stepListner != null) {
                if(!this.stepListner.onSuffering(step, isSuccess, current, total)){
                    break;
                }
            }
        }
    }

}
