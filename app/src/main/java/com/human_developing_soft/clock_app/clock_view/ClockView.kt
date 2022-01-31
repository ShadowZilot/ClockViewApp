package com.human_developing_soft.clock_app.clock_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.human_developing_soft.clock_app.R
import com.human_developing_soft.clock_app.clock_view.styles.standard.*
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ClockView : View {
    private val mNumbers : ClockDrawing
    private val mSecondArrow : ClockDrawing
    private val mMinuteArrow : ClockDrawing
    private val mHourArrow : ClockDrawing
    private val mClockFace : ClockDrawing

    private val mTime = GregorianCalendar()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.theme.obtainStyledAttributes(attributeSet,
            R.styleable.ClockView, 0, 0)
        try {
            mNumbers = StandardNumbers(
                Paint().apply {
                    isAntiAlias = true
                    color = attrs.getColor(
                        R.styleable.ClockView_numbersColor, Color.BLACK)
                    textSize = attrs.getDimension(R.styleable.ClockView_numbersSize,
                        60f)
                    style = Paint.Style.FILL_AND_STROKE
                }
            )
            mSecondArrow = StandardSecondArrow(
                Paint().apply {
                    isAntiAlias = true
                    color = attrs.getColor(R.styleable.ClockView_secondArrowColor,
                        Color.BLUE)
                    strokeWidth = attrs.getDimension(R.styleable.ClockView_secondArrowWidth,
                        5f)
                    style = Paint.Style.STROKE
                }
            )
            mMinuteArrow = StandardMinuteArrow(
                Paint().apply {
                    isAntiAlias = true
                    color = attrs.getColor(R.styleable.ClockView_minuteArrowColor,
                        Color.BLUE)
                    strokeWidth = attrs.getDimension(R.styleable.ClockView_minuteArrowWidth,
                        5f)
                    style = Paint.Style.STROKE
                }
            )
            mHourArrow = StandardHourArrow(
                Paint().apply {
                    isAntiAlias = true
                    color = attrs.getColor(R.styleable.ClockView_hourArrowColor,
                        Color.BLUE)
                    strokeWidth = attrs.getDimension(R.styleable.ClockView_hourArrowWidth,
                        5f)
                    style = Paint.Style.STROKE
                }
            )
            mClockFace = StandardClockFace(
                Paint().apply {
                    isAntiAlias = true
                    color = attrs.getColor(R.styleable.ClockView_clockColor,
                        Color.WHITE)
                    strokeWidth = attrs.getDimension(R.styleable.ClockView_clockWith,
                        10f)
                },
                attrs.getColor(R.styleable.ClockView_clocStrokeColor, Color.BLACK)
            )
        } finally {
            attrs.recycle()
        }
        initClock()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        mClockFace.draw(
            canvas!!, width.toFloat(),
            height.toFloat(), mTime
        )
        mNumbers.draw(
            canvas, width.toFloat(), height.toFloat(),
            mTime
        )
        mSecondArrow.draw(
            canvas, width.toFloat(),
            height.toFloat(), mTime
        )
        mMinuteArrow.draw(
            canvas, width.toFloat(),
            height.toFloat(), mTime
        )
        mHourArrow.draw(
            canvas, width.toFloat(),
            height.toFloat(), mTime
        )
    }

    private fun initClock() {
        mTime.time = Date()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                setCurrentTime(System.currentTimeMillis())
            }
        }, 0, 10)
    }

    fun setCurrentTime(time: Long) {
        mTime.timeInMillis = time
        invalidate()
    }
}