package com.teamir.mendcurse.table;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;

public class ItemView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String isPop = this.getIntent().getStringExtra("isPop");
/*		if (isPop == null) {
			// SearchElement界面，非dialog形式
			this.setTheme(R.style.AppTheme);
			Log.v("ddd", "非dialog形式");
		}*/
		super.onCreate(savedInstanceState);
//
//		if (isPop == null) {
//			// 去掉状态栏
//			this.getWindow().setFlags(
//					WindowManager.LayoutParams.FLAG_FULLSCREEN,
//					WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_view);

		// 从AppData类中取数据
		AppData appData = (AppData) getApplication();
		String index = getIntent().getStringExtra("index");
		final Element element = appData.getElement(Integer.parseInt(index));
		// 从assets取图片
		ImageView image = (ImageView) findViewById(R.id.pic_left);
		String imagePath = element.getElementid() + ".jpg";
		InputStream assetFile = null;
		try {
			AssetManager am = getAssets();
			assetFile = am.open(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
		// 如果图片还未回收，先强制回收该图片
		if (bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()) {
			bitmapDrawable.getBitmap().recycle();
		}
		// 改变图片
		image.setImageBitmap(BitmapFactory.decodeStream(assetFile));

		// 将数据写到TextView
		TextView text = (TextView) findViewById(R.id.element_attr);
		text.setText(element.toString());

		// 点击进入wiki百科页面
		TextView wiki = (TextView) findViewById(R.id.wiki_btn);
		wiki.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					// 能联网
					final WebView web = (WebView) findViewById(R.id.wiki);
					String url = "http://zh.wikipedia.org/wiki/"
							+ element.getElementname();
					web.loadUrl(url);
				} else {
					// 不能联网
					Toast.makeText(ItemView.this, "网络异常，请检查网络",
							Toast.LENGTH_SHORT).show();
				}

			}

		});

	}
}
