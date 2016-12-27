package org.ayo.robot.anim.ease.adapter;

import android.animation.TypeEvaluator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.ayo.animate.ease.Functions;
import org.ayo.robot.anim.R;
import org.ayo.robot.anim.Toaster;
import org.ayo.robot.anim.ease.EvaluatorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeEvaluator list adapter
 */
public class EvaluatorAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Activity mActivity;

    private List<String> mNames;
    private Functions[] mInterpolators;

    public EvaluatorAdapter(Activity context) {
        mInflater = LayoutInflater.from(context);
        mActivity = context;

        mInterpolators = Functions.values();
        mNames = new ArrayList<String>();
        for (Functions function : Functions.values()) {
            mNames.add(function.name());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_ease, null);
            mHolder.textView = (TextView) convertView.findViewById(R.id.list_item_text);
            mHolder.iv_submit = (ImageView) convertView.findViewById(R.id.iv_submit);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        final TypeEvaluator evaluator = mInterpolators[position].getEasingFunction();
        mHolder.textView.setText(String.format("%s Evaluator", mNames.get(position).replace("_", " ")));
        //convertView.setTag(o);//

        mHolder.iv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivity instanceof EvaluatorActivity){
                    ((EvaluatorActivity)mActivity).onSubmit(evaluator);
                }else{
                    Toaster.toastShort("不支持for result模式");
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        public TextView textView;
        public ImageView iv_submit;
    }

    @Override
    public int getCount() {
        return mInterpolators == null ? 0 : mInterpolators.length;
    }

    @Override
    public Object getItem(int position) {
        return mInterpolators == null ? null : mInterpolators[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
