package com.teamir.mendcurse.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;

public class MyView extends View {

	private Paint p;
	private String elementId;
	private String elementSymbol;
	private int symbolX;
	private int symbolY;
	private int idX;
	private int idY;
	private Context context;

	public MyView(Context context, String elementId) {
		super(context);
		// TODO Auto-generated constructor stub

		this.context = context;
		this.elementId = elementId;
		Element element = ((AppData) (((Activity) context).getApplication()))
				.getElement(elementId);
		this.elementSymbol = element.getSymbol();
		
	}
	
	public MyView(Context context, int elementId){
		this(context, Integer.toString(elementId));
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getAttrs(context, attrs);
		this.context = context;

		// 获得实际分辨率
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		// Log.v("分辨率", displayMetrics.widthPixels + " " +
		// displayMetrics.heightPixels );

		setIdX((int) (displayMetrics.widthPixels * 0.05 / 2));
		if (Integer.parseInt(elementId) >= 100) {
			setIdX((int) (displayMetrics.widthPixels * 0.05 / 3));
		}
		setIdY((int) (displayMetrics.heightPixels * 0.07 / 2));
		setSymbolX(0);
		setSymbolY((int) (displayMetrics.heightPixels * 0.07));
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getAttrs(context, attrs);
	}

	public void getAttrs(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.tableViewAttr);
		elementId = ta.getString(R.styleable.tableViewAttr_elementId);
		Element element = ((AppData) (((Activity) context).getApplication()))
				.getElement(elementId);
		this.setElementSymbol(element.getSymbol());
		ta.recycle();
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		p = new Paint();
		p.setAntiAlias(true);
		p.setTextSize(15);

		Log.v("测试","onDraw");
		canvas.drawText(getElementId(), getIdX(), getIdY(), p);
		canvas.drawText(getElementSymbol(), getSymbolX(), getSymbolY(), p);

	}

	@Override
	public boolean onTouchEvent(MotionEvent paramMotionEvent) {

		int[] arrayOfInt = new int[2];
		getLocationOnScreen(arrayOfInt);

		Intent intent = new Intent(context, ItemView.class);
		intent.putExtra("index", elementId.toString());
		intent.putExtra("isPop", "yes");
		context.startActivity(intent);
		Log.v("onTouch", "触发一次onTouch");

		return false;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementSymbol() {
		return elementSymbol;
	}

	public void setElementSymbol(String elementSymbol) {
		this.elementSymbol = elementSymbol;
	}

	public int getSymbolX() {
		return symbolX;
	}

	public void setSymbolX(int symbolX) {
		this.symbolX = symbolX;
	}

	public int getSymbolY() {
		return symbolY;
	}

	public void setSymbolY(int symbolY) {
		this.symbolY = symbolY;
	}

	public int getIdX() {
		return idX;
	}

	public void setIdX(int idX) {
		this.idX = idX;
	}

	public int getIdY() {
		return idY;
	}

	public void setIdY(int idY) {
		this.idY = idY;
	}

}
