package com.human_developing_soft.clock_app.clock_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.human_developing_soft.clock_app.clock_view.styles.standard.*
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ClockView : View {
    private val mNumbers = StandardNumbers(
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            textSize = 60f
            strokeWidth = 3f
            style = Paint.Style.FILL_AND_STROKE
        }
    )
    private val mSecondArrow = StandardSecondArrow(
        Paint().apply {
            isAntiAlias = true
            color = Color.BLUE
            strokeWidth = 5f
            style = Paint.Style.STROKE
        }
    )
    private val mMinuteArrow = StandardMinuteArrow(
        Paint().apply {
            isAntiAlias = true
            color = Color.BLUE
            strokeWidth = 5f
            style = Paint.Style.STROKE
        }
    )
    private val mHourArrow = StandardHourArrow(
        Paint().apply {
            isAntiAlias = true
            color = Color.BLUE
            strokeWidth = 5f
            style = Paint.Style.STROKE
        }
    )
    private val mClockFace = StandardClockFace(
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
    )

    private val mTime = GregorianCalendar()


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
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