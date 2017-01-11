package org.ayo.robot.anim.demolist;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class RecyclerHelper {

    private RecyclerView mRecyclerView;
    private Activity mActivity;

    private RecyclerHelper(){

    }

    public static RecyclerHelper demoAttach(Activity a, RecyclerView r){
        RecyclerHelper rh = new RecyclerHelper();
        rh.mActivity = a;
        rh.mRecyclerView = r;

        rh.layout_linear(false)
                .adapter(new SimpleRecyclerAdapter(a, SimpleModel.createList()));

        return rh;
    }

    public static RecyclerHelper attach(Activity a, RecyclerView r){
        RecyclerHelper rh = new RecyclerHelper();
        rh.mActivity = a;
        rh.mRecyclerView = r;
        return rh;
    }

    private RecyclerView.LayoutManager mLayoutMananger;

    public RecyclerHelper layout_linear(boolean isHorizontal, boolean reverseLayout){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        LinearLayoutManager llm = new LinearLayoutManager(mActivity, isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, reverseLayout);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper layout_linear(boolean isHorizontal){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        LinearLayoutManager llm = new LinearLayoutManager(mActivity, isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper layout_grid(boolean isHorizontal, int columnCount, boolean reverseLayout){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        GridLayoutManager llm = new GridLayoutManager(mActivity, columnCount, (isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL), reverseLayout);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper layout_grid(boolean isHorizontal, int columnCount){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        GridLayoutManager llm = new GridLayoutManager(mActivity, columnCount, (isHorizontal ? GridLayoutManager.HORIZONTAL : GridLayoutManager.VERTICAL), false);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper layout_staggerd_grid(boolean isHorizontal, int columnCount, boolean reverseLayout){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(columnCount, (isHorizontal ? StaggeredGridLayoutManager.HORIZONTAL : StaggeredGridLayoutManager.VERTICAL));
        llm.setReverseLayout(reverseLayout);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper layout_staggerd_grid(boolean isHorizontal, int columnCount){
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(columnCount, (isHorizontal ? StaggeredGridLayoutManager.HORIZONTAL : StaggeredGridLayoutManager.VERTICAL));
        llm.setReverseLayout(false);
        mLayoutMananger = llm;
        mRecyclerView.setLayoutManager(mLayoutMananger);
        return this;
    }

    public RecyclerHelper itemAnimator(RecyclerView.ItemAnimator animator){
        mRecyclerView.setItemAnimator(animator);
        return this;
    }
    public RecyclerHelper itemAnimator(){
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return this;
    }

    private RecyclerView.Adapter mAdatper;

    public RecyclerHelper adapter(RecyclerView.Adapter adapter){
        mAdatper = adapter;
        mRecyclerView.setAdapter(mAdatper);
        return this;
    }

    public RecyclerView.Adapter getAdapter(){
        return mAdatper;
    }

    public RecyclerView.LayoutManager getLayoutManager(){
        return mLayoutMananger;
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder{

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
        public ImageView iv;
        public TextView tv;

        public <T> T id(int id){
            return (T) itemView.findViewById(id);
        }
    }

    public static class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleViewHolder>{

        private List<SimpleModel> list;
        private Activity mActivity;

        public SimpleRecyclerAdapter(Activity a, List<SimpleModel> list){
            this.mActivity = a;
            this.list = list;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout v = new LinearLayout(mActivity);
            v.setOrientation(LinearLayout.HORIZONTAL);
            v.setGravity(Gravity.CENTER);
            v.setPadding(0, 10, 0, 0);

            ImageView iv = new ImageView(mActivity);
            LinearLayout.LayoutParams lp = RecyclerHelper.createLayoutParams_match_match();
            lp.width = 300;
            lp.leftMargin = 20;
            v.addView(iv, lp);

            TextView tv = new TextView(mActivity);
            lp = RecyclerHelper.createLayoutParams_match_match();
            lp.width = 0;
            lp.weight = 1;
            lp.leftMargin = 20;
            lp.rightMargin = 20;
            tv.setGravity(Gravity.CENTER);
            v.addView(tv, lp);

            SimpleViewHolder h = new SimpleViewHolder(v);
            h.iv = iv;
            h.tv = tv;
            return h;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            SimpleModel bean = list.get(position);
            holder.iv.setImageResource(bean.imgId);
            holder.tv.setText(bean.title);
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public void notifyDataSetChanged(List<SimpleModel> list){
            this.list = list;
            notifyDataSetChanged();
        }
    }

    public static class SimpleModel implements Serializable{
        public int imgId = android.R.drawable.ic_menu_week;
        public String imgUrl = "http://cms-bucket.nosdn.127.net/catchpic/d/df/df749d374c602718bd80999631adcb89.jpg?imageView&thumbnail=550x0";
        public String title = "李克强要求实事求是回应公众重大关切";
        public String desc = "李克强明确要求国务院各部门负责人，要及时主动回应公众重大关切，给社会各界一个稳定预期。中国政府网";

        public static List<SimpleModel> createList(){
            List<SimpleModel> list = new ArrayList<>();
            for(int i = 0; i < 30; i++){
                list.add(new SimpleModel());
            }
            return list;
        }

    }


    public static LinearLayout.LayoutParams createLayoutParams_wrap_match(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return lp;
    }
    public static LinearLayout.LayoutParams createLayoutParams_wrap_wrap(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        return lp;
    }
    public static LinearLayout.LayoutParams createLayoutParams_match_match(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return lp;
    }
    public static LinearLayout.LayoutParams createLayoutParams_match_wrap(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        return lp;
    }
}
