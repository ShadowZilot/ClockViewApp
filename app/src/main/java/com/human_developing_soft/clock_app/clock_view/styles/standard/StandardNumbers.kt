package com.human_developing_soft.clock_app.clock_view.styles.standard

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.human_developing_soft.clock_app.clock_view.ClockDrawing
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class StandardNumbers(
    paint: Paint
) : ClockDrawing(paint) {
    private val mNumbers = arrayOf(
        "12", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "10", "11"
    )

    private val mBounds = Rect()

    override fun draw(
        canvas: Canvas,
        width: Float, height: Float, time: Calendar
    ) {
        mPaint.textAlign = Paint.Align.CENTER
        canvas.drawLine(width / 2f, 0f, width / 2f, height, mPaint)
        canvas.drawLine(0f, height / 2f, width, height / 2f, mPaint)
        canvas.drawLine(0f, 0f, width, height, mPaint)
        canvas.drawLine(width, 0f, 0f, height, mPaint)
        canvas.drawLine(0f, 0f, width, 0f, mPaint)
        canvas.drawLine(0f, 0f, 0f, height, mPaint)
        canvas.drawLine(0f, height, width, height, mPaint)
        canvas.drawLine(width, height, width, 0f, mPaint)

        canvas.save().apply {
            var angle = 0f
            for (j in mNumbers.indices) {
                canvas.save().let { outer ->
                    mPaint.getTextBounds(mNumbers[j],
                        0, mNumbers[j].length, mBounds)
                    val textHeight = mBounds.height()
                    val textWidth = mPaint.measureText(mNumbers[j])
                    canvas.rotate(angle, width / 2f, height / 2f)
                    canvas.save().let { inner ->
                        canvas.rotate(
                            -angle,
                            (width / 2f) - textWidth / 2f,
                            (textHeight / 2f) + textHeight / 2f
                        )
                        canvas.drawText(
                            mNumbers[j],
                            (width / 2f) - textWidth / 2f,
                            (textHeight / 2f) + textHeight, mPaint
                        )
                        canvas.restoreToCount(inner)
                    }
                    angle += 30f
                    canvas.restoreToCount(outer)
                }
                canvas.restoreToCount(this)
            }
        }
    }
}