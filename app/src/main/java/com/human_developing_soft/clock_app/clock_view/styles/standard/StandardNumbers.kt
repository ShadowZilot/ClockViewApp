package com.human_developing_soft.clock_app.clock_view.styles.standard

import android.graphics.Canvas
import android.graphics.Paint
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

    override fun draw(canvas: Canvas,
                      width: Float, height: Float, time: Calendar) {
        canvas.save().apply {
            var angle = 0f
            for (j in mNumbers.indices) {
                canvas.save().let { outer ->
                    canvas.rotate(angle, width / 2f, height / 2f)
                    canvas.drawText(mNumbers[j],
                        (width / 2f) - mPaint.measureText(mNumbers[j]) / 2f,
                        80f, mPaint)
                    angle += 30f
                    canvas.restoreToCount(outer)
                }
                canvas.restoreToCount(this)
            }
        }
    }
}