package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import bwie.com.zhangshuaikang20180709.R;

public class myView extends View{

    private Paint paint;
    private Bitmap bpBackground;
    private Bitmap bpForeground;
    private Canvas canvas;
    private Paint contentPaint;
    private String content = "刮刮看~";
    private Path path;

    public myView(Context context) {
        super(context);
        init();
    }

    public myView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        paint = new Paint();
        paint.setAlpha(0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        path = new Path();

        bpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.text);
        bpForeground = Bitmap.createBitmap(bpBackground.getWidth(), bpBackground.getHeight(), Bitmap.Config.ARGB_8888);



        canvas = new Canvas(bpForeground);

        contentPaint = new Paint();
        contentPaint.setColor(Color.WHITE);
        contentPaint.setTextSize(100);
        contentPaint.setStrokeWidth(20);

        canvas.drawColor(Color.GRAY);
        canvas.drawText(content,canvas.getWidth(),canvas.getHeight(),contentPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }
        canvas.drawPath(path,paint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bpBackground,0,0,null);
        canvas.drawBitmap(bpForeground,0,0,null);

    }
}
