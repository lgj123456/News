package ad0424.yls.example.com.news.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import ad0424.yls.example.com.news.model.TemperatureBean;
import ad0424.yls.example.com.news.model.TemperaturePoint;

/**
 * Created by yls on 2017/6/1.
 */

public class TemperatureView extends View {
    private Paint mPaint;
    private ArrayList<TemperatureBean> mTemperatureBeanArrayList = new ArrayList<>();
    private ArrayList<TemperaturePoint> mTemperaturePoints = new ArrayList<>();
    private int x = 50;
    private int lowY;
    private int highY;
    private int radius = 10;
    private ArrayList<String> dateList = new ArrayList<>();

    public TemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //  mPaint.setStyle(Paint.Style.STROKE);

        initTempList();
    }

    private void initTempList() {
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        drawPoints(canvas);
        drawLines(canvas);
        drawDate(canvas);
    }

    private void drawDate(Canvas canvas) {
        mPaint.setTextSize(25);
        mPaint.setStrokeWidth(2);
        for (int i = 0; i < dateList.size(); i++) {
            canvas.drawText(dateList.get(i), (x + 150 * i) - 15, (50 - mTemperatureBeanArrayList.get(0).getLow()) * 10 + 100, mPaint);

        }
    }

    private void drawLines(Canvas canvas) {
        Path lowPath = new Path();
        Path highPath = new Path();
        for (int i = 0; i < mTemperatureBeanArrayList.size(); i++) {
            if (i == 0) {
                lowPath.moveTo(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getLow()) * 10);
                highPath.moveTo(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getHigh()) * 10);
            }
            lowPath.lineTo(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getLow()) * 10);
            highPath.lineTo(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getHigh()) * 10);
            mPaint.setColor(Color.BLUE);
            mPaint.setTextSize(30);
            mPaint.setStrokeWidth(3);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawText(mTemperatureBeanArrayList.get(i).getLow() + "℃", (x + 150 * i) - 15, (50 - mTemperatureBeanArrayList.get(i).getLow()) * 10 + 50, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawText(mTemperatureBeanArrayList.get(i).getHigh() + "℃", (x + 150 * i) - 15, (50 - mTemperatureBeanArrayList.get(i).getHigh()) * 10 - 50, mPaint);

        }
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(lowPath, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawPath(highPath, mPaint);
    }

    private void drawPoints(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mTemperatureBeanArrayList.size(); i++) {
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getLow()) * 10, radius, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(x + 150 * i, (50 - mTemperatureBeanArrayList.get(i).getHigh()) * 10, radius, mPaint);

        }
    }

    public void drawWeather(ArrayList<TemperatureBean> temperatureBeanArrayList) {
        mTemperatureBeanArrayList = temperatureBeanArrayList;
        invalidate();
    }

    public void drawDateData(ArrayList<String> dateList) {
        this.dateList = dateList;
        invalidate();
    }
}
