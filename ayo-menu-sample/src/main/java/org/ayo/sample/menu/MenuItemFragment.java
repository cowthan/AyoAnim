package org.ayo.sample.menu;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ayo.core.attacher.ActivityAttacher;


public class MenuItemFragment extends SBFragment {

	private MenuItem menuItem;

	public void setMenu(MenuItem menuItem){
		this.menuItem = menuItem;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.frag_menu_item;
	}

	@Override
	public void onCreateView(View root) {
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		for(Leaf leaf: menuItem.subMenus){
			addButton(leaf);
		}
	}

	private LinearLayout ll_root;


	private void addButton(final Leaf leaf){
		Button btn = new Button(getActivity());
		btn.setText(leaf.name);
		btn.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		btn.setBackgroundResource(R.drawable.sel_menu2);
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
				if(leaf.attacherClass != null){
					ActivityAttacher.startActivity(getActivity(), leaf.attacherClass);
				}else if(leaf.activityClass != null){
					Intent intent = new Intent(getActivity(), leaf.activityClass);
					getActivity().startActivity(intent);
				}else{
					Toast.makeText(getActivity(), "One Piece of Shit", Toast.LENGTH_SHORT).show();
				}
			}
		});

		if(leaf.attacherClass == null){

		}else{
			btn.setBackgroundResource(R.drawable.sel_menu3);
		}

	}


}
