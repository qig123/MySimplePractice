package com.qig.cubedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class CubeView extends View {


    private final int SIDE_LENGTH_LONG = 300;
    private final int SIDE_LENGTH_SHORT = 300;

    private final int ANGLE_A = 50;
    private final int ANGLE_B = 90 - ANGLE_A;
    private final float sin30 = (float) Math.sin(ANGLE_A * Math.PI / 180.0);
    private final float sin60 = (float) Math.sin(ANGLE_B * Math.PI / 180.0);

    private Path mTopPath;
    private Paint mTopPaint;

    private Path mLeftPath;
    private Paint mLeftPaint;

    private Path mRightPath;
    private Paint mRightPaint;


    private float mOriginPointX = 0;
    private float mOriginPointY = 0;

    public CubeView(Context context) {
        this(context, null);
    }

    public CubeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CubeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // init top
        mTopPaint = new Paint();
        mTopPaint.setColor(Color.parseColor("#FDFDE3"));


        mTopPath = new Path();
        mTopPath.moveTo(0, 0);
        mTopPath.lineTo(SIDE_LENGTH_LONG * sin30, -SIDE_LENGTH_LONG * sin60);
        mTopPath.lineTo(-(SIDE_LENGTH_SHORT - SIDE_LENGTH_LONG * sin30), -SIDE_LENGTH_LONG * sin60);
        mTopPath.lineTo(-SIDE_LENGTH_SHORT, 0);
        mTopPath.close();

        // init left
        mLeftPaint = new Paint();
        mLeftPaint.setColor(Color.parseColor("#EEDC70"));

        mLeftPath = new Path();
        mLeftPath.moveTo(0, 0);
        mLeftPath.lineTo(0, SIDE_LENGTH_LONG);
        mLeftPath.lineTo(-SIDE_LENGTH_SHORT, SIDE_LENGTH_LONG);
        mLeftPath.lineTo(-SIDE_LENGTH_SHORT, 0);
        mLeftPath.close();


        // init right
        mRightPaint = new Paint();
        mRightPaint.setColor(Color.parseColor("#FAECA4"));
        mRightPath = new Path();
        mRightPath.moveTo(0, 0);
        mRightPath.lineTo(SIDE_LENGTH_LONG * sin30, -SIDE_LENGTH_LONG * sin60);
        mRightPath.lineTo(+(SIDE_LENGTH_LONG * sin30), -(SIDE_LENGTH_LONG + SIDE_LENGTH_LONG * sin60));
        mRightPath.lineTo(0, -SIDE_LENGTH_LONG);
        mRightPath.close();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mOriginPointX = getMeasuredWidth() / 2;
        mOriginPointY = getMeasuredHeight() / 2;
        //move the origin point
        canvas.translate(mOriginPointX, mOriginPointY);
        int boomIndex = canvas.save();
        //first
        drawCube(canvas, 0, 0);
        canvas.restoreToCount(boomIndex);
    }

    private void drawCube(Canvas canvas, float left, float top) {
        canvas.translate(left, top);
        int secondIndex = canvas.save();
        //init top part
        canvas.drawPath(mTopPath, mTopPaint);
        canvas.restoreToCount(secondIndex);
        //draw top line
        secondIndex = canvas.save();
        Paint linePaintTop = new Paint();
        linePaintTop.setColor(Color.BLACK);
        linePaintTop.setAntiAlias(true);
        linePaintTop.setStrokeWidth((float) 5.0);
        canvas.translate(-SIDE_LENGTH_SHORT / 2, 0);
        canvas.drawLine(0, 0, SIDE_LENGTH_LONG * sin30, -SIDE_LENGTH_LONG * sin60, linePaintTop);
        canvas.restoreToCount(secondIndex);
        //init left part
        secondIndex = canvas.save();
        canvas.drawPath(mLeftPath, mLeftPaint);
        canvas.restoreToCount(secondIndex);
        secondIndex = canvas.save();
        //draw left line
        Paint linePaintLeft = new Paint();
        linePaintLeft.setColor(Color.BLACK);
        linePaintLeft.setAntiAlias(true);
        linePaintLeft.setStrokeWidth((float) 5.0);
        canvas.translate(-SIDE_LENGTH_SHORT / 2, 0);
        canvas.drawLine(0, 0, 0, SIDE_LENGTH_LONG, linePaintLeft);
        canvas.restoreToCount(secondIndex);

        //init right part
        canvas.translate(0, SIDE_LENGTH_LONG);
        canvas.drawPath(mRightPath, mRightPaint);
        canvas.restoreToCount(secondIndex);

    }

}
