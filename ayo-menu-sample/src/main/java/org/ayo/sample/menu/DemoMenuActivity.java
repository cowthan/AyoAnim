package org.ayo.sample.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ayo.core.activity.AyoActivityAttacher;

import java.io.Serializable;


public abstract class DemoMenuActivity extends AyoActivityAttacher {

	public class DemoInfo implements Serializable {

		public String name;
		public View.OnClickListener onClickListener;
		public int iconNormal;
		public int iconPressed;

		public DemoInfo(String name, View.OnClickListener onClickListener){
			this.name = name;
			this.onClickListener = onClickListener;
		}
	}

	public abstract DemoInfo[] getDemoMenus();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_menu_item);

		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		for(DemoInfo leaf: getDemoMenus()){
			addButton(leaf);
		}
	}

	private LinearLayout ll_root;

	private void addButton(final DemoInfo leaf){
		Button btn = new Button(getActivity());
		btn.setText(leaf.name);
		btn.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		btn.setBackgroundResource(R.drawable.sel_menu3);
		btn.setTextSize(15);
		btn.setPadding(20, 0, 20, 0);
		btn.setTextColor(Color.WHITE);
		btn.setAllCaps(false);


		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(), 40));
		lp.gravity = Gravity.CENTER;
		lp.topMargin = Utils.dip2px(getActivity(), 5);
		lp.bottomMargin = Utils.dip2px(getActivity(), 5);
		lp.leftMargin = Utils.dip2px(getActivity(), 5);
		lp.rightMargin = Utils.dip2px(getActivity(), 5);
		ll_root.addView(btn, lp);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(leaf.onClickListener == null){
					Toast.makeText(getActivity(), "成功扣款20元，慢走不送", Toast.LENGTH_SHORT).show();
				}else{
					leaf.onClickListener.onClick(v);
				}
			}
		});

	}


}
