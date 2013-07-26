package com.teamir.mendcurse;

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
import android.widget.PopupWindow;
import android.widget.Toast;

public class MyView extends View{
	
	private Paint p;
	private String elementId;
	private String elementSymbol;
	private int symbolX;
	private int symbolY;
	private int idX;
	private int idY;	
	private Context context;
	private boolean isPop = false;

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 public MyView(Context context, AttributeSet attrs){
	        super(context, attrs);
	        getAttrs(context, attrs);
	        this.context = context;
	        
	        //获得实际分辨率
	        DisplayMetrics displayMetrics = new DisplayMetrics();  
	        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
	        Log.v("分辨率", displayMetrics.widthPixels + " " + displayMetrics.heightPixels );
	        
	        setIdX((int)(displayMetrics.widthPixels*0.05/2));
        	setIdY((int)(displayMetrics.heightPixels*0.07/2));
        	setSymbolX(0);
        	setSymbolY((int)(displayMetrics.heightPixels*0.07));
    }

    public MyView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        getAttrs(context, attrs);
    }
    
    public void getAttrs(Context context, AttributeSet attrs){
    	TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.tableViewAttr);
    	elementId = ta.getString(R.styleable.tableViewAttr_elementId);
    	elementSymbol = ta.getString(R.styleable.tableViewAttr_elementSymbol);
    	ta.recycle();
    }

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);		
		p = new Paint();	        
	    p.setAntiAlias(true);      
	    p.setTextSize(15);         
	    
	    canvas.drawText(getElementId(), getIdX(), getIdY(), p);
	    canvas.drawText(getElementSymbol(), getSymbolX(), getSymbolY(), p);
		
	    
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent paramMotionEvent){
	    if(isPop == false){
	    	isPop = true;
	    	int[] arrayOfInt = new int[2];
		    getLocationOnScreen(arrayOfInt);
		    Toast toast = Toast.makeText(context, elementId.toString(), Toast.LENGTH_SHORT);
			toast.show();
			
			//popup.showAsDropDown(this);
			//popup.showAtLocation(parent, gravity, x, y)
			
			Intent intent = new Intent(context, ItemView.class);
			intent.putExtra("index", elementId.toString());
			context.startActivity(intent);
	    }
	    return true;
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
