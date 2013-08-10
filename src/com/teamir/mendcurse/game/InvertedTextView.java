package com.teamir.mendcurse.game;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class InvertedTextView extends TextView
{
  public InvertedTextView(Context paramContext)
  {
    super(paramContext);
    setWillNotDraw(false);
  }

  public InvertedTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setWillNotDraw(false);
  }

  public InvertedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWillNotDraw(false);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getPaddingRight();
    int m = getPaddingBottom();
    int n = getWidth() - i - k;
    int i1 = getHeight() - j - m;
    int i2 = paramCanvas.getSaveCount();
    paramCanvas.translate(i + n / 2, j + i1 / 2);
    paramCanvas.rotate(-180.0F);
    paramCanvas.translate(-n / 2 - i, -i1 / 2 - j);
    paramCanvas.restoreToCount(i2);
    super.onDraw(paramCanvas);
  }
}
