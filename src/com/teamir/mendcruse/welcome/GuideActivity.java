package com.teamir.mendcruse.welcome;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.teamir.mendcurse.MainActivity;
import com.teamir.mendcurse.R;

public class GuideActivity extends Activity {
	ViewPager viewPager;
	ArrayList<View> list;
	ImageView imageView;
	ImageView[] imageViews;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		LayoutInflater inflater = getLayoutInflater();
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.item1, null));
		list.add(inflater.inflate(R.layout.item2, null));
		list.add(inflater.inflate(R.layout.item3, null));

		imageViews = new ImageView[list.size()];
		ViewGroup main = (ViewGroup) inflater.inflate(R.layout.guid_view, null);
		ViewGroup group = (ViewGroup) main.findViewById(R.id.viewGroup);

		viewPager = (ViewPager) main.findViewById(R.id.viewPager);

		for (int i = 0; i < list.size(); i++) {
			imageView = new ImageView(GuideActivity.this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			imageView.setPadding(10, 0, 10, 0);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.white);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.grey);
			}
			group.addView(imageView);
		}

		setContentView(main);

		viewPager.setAdapter(new MyAdapter());
		viewPager.setOnPageChangeListener(new MyListener());

	}

	public void startBtn(View v) {
		Intent intent = new Intent(GuideActivity.this, MainActivity.class);
		startActivity(intent);
		GuideActivity.this.finish();
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(list.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	class MyListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			imageViews[arg0].setBackgroundResource(R.drawable.white);
			for (int i = 0; i < imageViews.length; i++) {
				if (arg0 != i) {
					imageViews[i].setBackgroundResource(R.drawable.grey);
				}
			}
		}
	}
}