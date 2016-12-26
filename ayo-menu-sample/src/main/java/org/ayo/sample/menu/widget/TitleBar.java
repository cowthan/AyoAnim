package org.ayo.sample.menu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ayo.sample.menu.R;


public class TitleBar extends FrameLayout{

	public interface Callback{
		void onLeftButtonClicked(View v);
		void onRightButtonClicked(int index, View v);
	}
	
	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TitleBar(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.ayo_sample_menu_view_title_bar, this, true);
	
		title_bar_iv_left = (ImageView) findViewById(R.id.title_bar_iv_left);
		title_bar_iv_title = (ImageView) findViewById(R.id.title_bar_iv_title);
		title_bar_tv_title = (TextView) findViewById(R.id.title_bar_tv_title);
		title_bar_ll_right = (LinearLayout) findViewById(R.id.title_bar_ll_right);
		
	}
	
	private Callback callback;
	private ImageView title_bar_iv_left;
	private ImageView title_bar_iv_title;
	private TextView title_bar_tv_title;
	private LinearLayout title_bar_ll_right;
	
	public TitleBar clearRightButtons(){
		this.title_bar_ll_right.removeAllViews();
		return this;
	}
	
	public TitleBar removeRightButton(int index){
		View v = this.title_bar_ll_right.findViewWithTag(index);
		if(v != null){
			this.title_bar_ll_right.removeView(v);
		}
		return this;
	}
	
	private ImageView generateRightButton(int id){
		ImageView iv = new ImageView(getContext());
		iv.setImageResource(id);
		return iv;
	}
	
	public TitleBar callback(Callback callback){
		this.callback = callback;
		return this;
	}
	
	public TitleBar leftButton(int id){
		if(id == 0){
			title_bar_iv_left.setVisibility(View.GONE);
		}else{
			title_bar_iv_left.setVisibility(View.VISIBLE);
			title_bar_iv_left.setImageResource(id);
			title_bar_iv_left.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					callback.onLeftButtonClicked(v);
				}
			});
		}
		
		return this;
	}
	public TitleBar titleColor(int colorId){
		this.title_bar_tv_title.setTextColor(this.getContext().getResources().getColor(colorId));
		return this;
	}
	
	public TitleBar title(String title){
		this.title_bar_iv_title.setVisibility(View.GONE);
		this.title_bar_tv_title.setVisibility(View.VISIBLE);
		this.title_bar_tv_title.setText(title);
		return this;
	}
	public TitleBar titleImage(int id){
		this.title_bar_iv_title.setVisibility(View.VISIBLE);
		this.title_bar_tv_title.setVisibility(View.GONE);
		this.title_bar_iv_title.setImageResource(id);
		return this;
	}
	public TitleBar rightButton(final int index, int iconId){
		ImageView iv = generateRightButton(iconId);
		iv.setTag(index);
		this.title_bar_ll_right.addView(iv);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				callback.onRightButtonClicked(index, v);
			}
		});
		return this;
	}
	public TitleBar bgColor(int colorId){
		this.findViewById(R.id.title_bar_root).setBackgroundResource(colorId);
		return this;
	}
}
