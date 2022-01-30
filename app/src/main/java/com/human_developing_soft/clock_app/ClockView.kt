package com.human_developing_soft.clock_app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ClockView : View {
    private val mNumbersPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        textSize = 60f
        strokeWidth = 3f
        style = Paint.Style.FILL_AND_STROKE
    }
    private val mArrowsPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }
    private val mClock = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val mTime = GregorianCalendar()
    private val mNumbers = arrayOf(
        "12", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "10", "11"
    )

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
        canvas?.drawCircle(width / 2f, height / 2f, (width / 2f) - 5, mClock)
        canvas?.save().apply {
            var angle = 0f
            for (j in mNumbers.indices) {
                canvas?.save().let { outer ->
                    canvas?.rotate(angle, width / 2f, height / 2f)
                    canvas?.drawText(mNumbers[j],
                        (width / 2f) - mNumbersPaint.measureText(mNumbers[j]) / 2f,
                        80f, mNumbersPaint)
                    angle += 30f
                    Log.d("Angle", angle.toString())
                    canvas?.restoreToCount(outer!!)
                }
                canvas?.restoreToCount(this!!)
            }
        }
        // draw second arrow
        canvas?.save().apply {
            canvas?.rotate(
                (6f * mTime.get(Calendar.SECOND)),
                width/2f,
                height/2f
            )
            canvas?.drawLine(width/2f, height/2f,
                width/2f,
                (height /2f) - (height * 0.35f),
                mArrowsPaint)
            canvas?.restoreToCount(this!!)
        }
        // draw minute arrow
        canvas?.save().apply {
            canvas?.rotate(
                6f * mTime.get(Calendar.MINUTE),
                width/2f,
                height/2f
            )
            canvas?.drawLine(width/2f, height/2f,
                width/2f,
                (height /2f) - (height * 0.30f),
                mArrowsPaint)
            canvas?.restoreToCount(this!!)
        }
        // draw hour arrow
        canvas?.save().apply {
            canvas?.rotate(
                30f * mTime.get(Calendar.HOUR),
                width/2f,
                height/2f
            )
            canvas?.drawLine(width/2f, height/2f,
                width/2f,
                (height /2f) - (height * 0.25f),
                mArrowsPaint)
            canvas?.restoreToCount(this!!)
        }
        Log.d("Time", "${mTime.get(Calendar.HOUR)}," +
                " ${mTime.get(Calendar.MINUTE)}," +
                " ${mTime.get(Calendar.SECOND)}, ${mTime.get(Calendar.MILLISECOND)}")
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