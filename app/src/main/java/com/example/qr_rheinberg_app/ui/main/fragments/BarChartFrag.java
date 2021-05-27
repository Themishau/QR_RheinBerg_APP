package com.example.qr_rheinberg_app.ui.main.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.example.qr_rheinberg_app.R;
import com.example.qr_rheinberg_app.ui.data.BI.MyMarkerView;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class BarChartFrag extends SimpleFragment implements OnChartGestureListener {

    @NonNull
    public static Fragment newInstance() {
        return new BarChartFrag();
    }

    private BarChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_bar, container, false);

        // create a new chart object
        chart = new BarChart(getActivity());
        chart.getDescription().setEnabled(false);
        chart.setOnChartGestureListener(this);

        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

       /* chart.setData(generateBarData(1, 20000, 12));*/

        chart.setData(generateData());
        Legend l = chart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        chart.setFitBars(true);
        chart.getDescription().setText("Wöchentliche Besucher");
        chart.animateY(2000);
        // programmatically add the chart
        FrameLayout parent = v.findViewById(R.id.parentLayout);
        parent.addView(chart);

        return v;
    }

    private String getLabel(int i) {
        return wochentage[i];
    }

    private BarData generateData() {

        ArrayList<BarEntry> TESTDATA_X = new ArrayList<>();
        TESTDATA_X.add(new BarEntry(1, 500));
        TESTDATA_X.add(new BarEntry(2, 550));
        TESTDATA_X.add(new BarEntry(3, 600));
        TESTDATA_X.add(new BarEntry(4, 650));
        TESTDATA_X.add(new BarEntry(5, 400));
        TESTDATA_X.add(new BarEntry(6, 300));
        TESTDATA_X.add(new BarEntry(7, 250));

        BarDataSet barDataSet = new BarDataSet(TESTDATA_X, getLabel(0));
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);

        return new BarData(barDataSet);
    }
    private final String[] wochentage = new String[] { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag" };



    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END");
        chart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart long pressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart fling. VelocityX: " + velocityX + ", VelocityY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

}
