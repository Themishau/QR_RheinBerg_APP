package com.example.qr_rheinberg_app.ui.main.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.qr_rheinberg_app.R
import com.example.qr_rheinberg_app.ui.data.BI.MyMarkerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture
import com.github.mikephil.charting.listener.OnChartGestureListener


class BIFragment : SimpleFragment(), OnChartGestureListener {
    private var chart: BarChart? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.frag_simple_bar, container, false)

        // create a new chart object
        chart = BarChart(getActivity())
        chart!!.description.isEnabled = true
        chart!!.onChartGestureListener = this
        val mv = MyMarkerView(getActivity(), R.layout.custom_marker_view)
        mv.setChartView(chart) // For bounds control
        chart!!.marker = mv
        chart!!.setDrawGridBackground(true)
        chart!!.setDrawBarShadow(true)
        val tf = Typeface.createFromAsset(context?.assets, "OpenSans-Light.ttf")
        chart!!.data = generateData()
        val l = chart!!.legend
        l.typeface = tf
        val leftAxis = chart!!.axisLeft
        leftAxis.typeface = tf
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        leftAxis.setLabelCount(8)
        chart!!.axisRight.isEnabled = false
        val xAxis = chart!!.xAxis
        xAxis.isEnabled = false

        // programmatically add the chart
        val parent: FrameLayout = v.findViewById(R.id.parentLayout)
        parent.addView(chart)
        return v
    }

    private fun generateData(): BarData {

        val TESTDATA_X = ArrayList<String>()
        TESTDATA_X.add("10 Uhr")
        TESTDATA_X.add("11 Uhr")

        /* andere Möglichkeiten wären: (Line)Entry usw. */
        val data = ArrayList<BarEntry>();
        data.add(BarEntry(11.00f,200f))
        data.add(BarEntry(12.00f, 300f))
        data.add(BarEntry(13.00f, 300f))
        data.add(BarEntry(14.00f, 300f))
        data.add(BarEntry(15.00f, 600f))
        data.add(BarEntry(16.00f, 300f))
        data.add(BarEntry(17.00f, 200f))

        /* date in das Dataset packen */
        val dataset = BarDataSet(data, "Bar")
        dataset.label
        dataset.valueTextColor = Color.BLACK
        dataset.valueTextSize = 10f
        dataset.axisDependency = YAxis.AxisDependency.LEFT


        val groupSpace = 0.01f
        val barSpace = 0.01f // x2 dataset
        val barWidth = 0.9f // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"
        val plotdata = BarData(dataset)
        plotdata.barWidth = barWidth

        return plotdata;
    }
    override fun onChartGestureStart(me: MotionEvent, lastPerformedGesture: ChartGesture) {
        Log.i("Gesture", "START")
    }

    override fun onChartGestureEnd(me: MotionEvent, lastPerformedGesture: ChartGesture) {
        Log.i("Gesture", "END")
        chart!!.highlightValues(null)
    }

    override fun onChartLongPressed(me: MotionEvent) {
        Log.i("LongPress", "Chart long pressed.")
    }

    override fun onChartDoubleTapped(me: MotionEvent) {
        Log.i("DoubleTap", "Chart double-tapped.")
    }

    override fun onChartSingleTapped(me: MotionEvent) {
        Log.i("SingleTap", "Chart single-tapped.")
    }

    override fun onChartFling(
        me1: MotionEvent,
        me2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ) {
        Log.i("Fling", "Chart fling. VelocityX: $velocityX, VelocityY: $velocityY")
    }

    override fun onChartScale(me: MotionEvent, scaleX: Float, scaleY: Float) {
        Log.i("Scale / Zoom", "ScaleX: $scaleX, ScaleY: $scaleY")
    }

    override fun onChartTranslate(me: MotionEvent, dX: Float, dY: Float) {
        Log.i("Translate / Move", "dX: $dX, dY: $dY")
    }

    companion object {
        fun newInstance(): Fragment {
            return BIFragment()
        }
    }
}
