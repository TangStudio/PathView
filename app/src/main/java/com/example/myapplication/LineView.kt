package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;



/**
 * @Author: Administrator
 * @Time: 2021/4/14 10:19
 * @Company：ch
 * @Description: 功能描述
 */
public class LineView extends View {

    private int mWidth;
    private int mHeight;
    private Context mContext;
    Path pathLine1,pathLine2,pathLine3,pathLine4,pathLine5;
    Paint pointBlack,pointYellow,pointRed,pointBlue,pointGreen;
    private int backGroundColor = Color.parseColor("#ffffff");
    private float lineWidth=5;
    private float scale=0.3F;
    float x1,y1,x2,x3,x4,x5,y2,y3,y4,y5;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap bitmap5;
    private Canvas canvas;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        pathLine1 = new Path();
        pathLine2 = new Path();
        pathLine3 = new Path();
        pathLine4 = new Path();
        pathLine5 = new Path();
        pointBlack = new Paint();
        pointBlack.setAntiAlias(true);
        pointBlack.setStrokeWidth(lineWidth);
        pointBlack.setStyle(Paint.Style.STROKE);
        pointBlack.setColor(Color.BLACK);

        pointYellow = new Paint();
        pointYellow.setAntiAlias(true);
        pointYellow.setStrokeWidth(lineWidth);
        pointYellow.setStyle(Paint.Style.STROKE);
        pointYellow.setColor(Color.YELLOW);

        pointRed=new Paint();
        pointRed.setAntiAlias(true);
        pointRed.setStrokeWidth(lineWidth);
        pointRed.setStyle(Paint.Style.STROKE);
        pointRed.setColor(Color.RED);

        pointBlue=new Paint();
        pointBlue.setAntiAlias(true);
        pointBlue.setStrokeWidth(lineWidth);
        pointBlue.setStyle(Paint.Style.STROKE);
        pointBlue.setColor(Color.BLUE);

        pointGreen=new Paint();
        pointGreen.setAntiAlias(true);
        pointGreen.setStrokeWidth(lineWidth);
        pointGreen.setStyle(Paint.Style.STROKE);
        pointGreen.setColor(Color.GREEN);

        bitmap1 = Tools.scaleBitmap(Tools.getResBitmapFor8888(getContext(), R.mipmap.player1),scale);
        bitmap2 = Tools.scaleBitmap(Tools.getResBitmapFor8888(getContext(), R.mipmap.player2),scale);
        bitmap3 = Tools.scaleBitmap(Tools.getResBitmapFor8888(getContext(), R.mipmap.player3),scale);
        bitmap4 = Tools.scaleBitmap(Tools.getResBitmapFor8888(getContext(), R.mipmap.player4),scale);
        bitmap5 = Tools.scaleBitmap(Tools.getResBitmapFor8888(getContext(), R.mipmap.player5),scale);
    }

    public void setFirstXY1(float x,float y){
        pathLine1.moveTo(x, y);
        this.x1 = x;
        this.y1 = y;
    }
    public void setFirstXY2(float x,float y){
        pathLine2.moveTo(x, y);
        this.x2 = x;
        this.y2 = y;
    }
    public void setFirstXY3(float x,float y){
        pathLine3.moveTo(x, y);
        this.x3 = x;
        this.y3 = y;
    }
    public void setFirstXY4(float x,float y){
        pathLine4.moveTo(x, y);
        this.x4 = x;
        this.y4 = y;
    }
    public void setFirstXY5(float x,float y){
        pathLine5.moveTo(x, y);
        this.x5 = x;
        this.y5 = y;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            mWidth = getWidth();
            mHeight = getHeight();

            setBackgroundColor(backGroundColor);
            setBackground(getResources().getDrawable(R.mipmap.playground));
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public void drawLine(float x, float y,String id) {

        switch (id){
            case "0":
                pathLine1.lineTo(x,y);
                this.x1 = x;
                this.y1 = y;
                break;
            case "1":
                pathLine2.lineTo(x,y);
                this.x2 = x;
                this.y2 = y;
                break;
            case "2":
                pathLine3.lineTo(x,y);
                this.x3 = x;
                this.y3 = y;
                break;
            case "3":
                pathLine4.lineTo(x,y);
                this.x4 = x;
                this.y4 = y;
                break;
            case "4":
                pathLine5.lineTo(x,y);
                this.x5 = x;
                this.y5 = y;
                break;
        }
        postInvalidate();
    }

    public void clearCanvas(){
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;
        canvas.drawPath(pathLine1, pointBlack);
        canvas.drawPath(pathLine2, pointYellow);
        canvas.drawPath(pathLine3, pointRed);
        canvas.drawPath(pathLine4, pointBlue);
        canvas.drawPath(pathLine5, pointGreen);
        //y-10是为了图片居中，具体根据图片大小设置
        canvas.drawBitmap(bitmap1,x1,y1 - 10,pointBlack);
        canvas.drawBitmap(bitmap2,x2,y2 - 10,pointYellow);
        canvas.drawBitmap(bitmap3,x3,y3 - 10,pointRed);
        canvas.drawBitmap(bitmap4,x4,y4 - 10,pointBlue);
        canvas.drawBitmap(bitmap5,x5,y5 - 10,pointGreen);

    }
}
