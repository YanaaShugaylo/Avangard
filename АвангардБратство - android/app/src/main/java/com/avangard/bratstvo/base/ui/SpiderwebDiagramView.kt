package com.avangard.bratstvo.base.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.avangard.bratstvo.R
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel
import kotlin.math.cos
import kotlin.math.sin

class SpiderwebDiagramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var path: Path = Path()

    private var screenWidth = 0f
    private var diagramViewWidth = 0f
    private lateinit var diagramCenterPoint: PointF
    private var textSize = 0f

    var skills: List<UserSkillUiModel>? = null
    private var startPoint: PointF = PointF(0f, 0f)
    private val pi = 3.14f

    private val diagramEdgePoints = ArrayList<PointF>()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        skills?.let {
            screenWidth = width.toFloat()
            diagramViewWidth = screenWidth - 150
            diagramCenterPoint = PointF(screenWidth / 2, (diagramViewWidth / 2 - 50))

            paint.color = ContextCompat.getColor(context, R.color.black)
            paint.isAntiAlias = true
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2f

            canvas?.run {
                drawCenterPoint(this)
                drawDiagram(this)
                drawSkillValues(this)

                textSize = resources.getDimensionPixelSize(R.dimen.offset_12).toFloat()

                paint.color = ContextCompat.getColor(context, R.color.black)
                paint.textSize = textSize
                paint.typeface = ResourcesCompat.getFont(context, R.font.open_sans)
                paint.style = Paint.Style.FILL
                paint.strokeWidth = 1f

                var xDelta: Float
                var yDelta: Float

                it.forEachIndexed { index, skill ->
                    val textWidth = paint.measureText(skill.title)

                    xDelta = when (index) {
                        0 -> -(textWidth / 2)
                        1 -> 20f
                        2 -> 10f
                        3 -> -(textWidth + 10)
                        else -> -(textWidth + 20f)
                    }

                    yDelta = when (index) {
                        0 -> -20f
                        1 -> -10f
                        2 -> textSize + 20f
                        3 -> textSize + 20f
                        else -> -10f
                    }
                    this.drawText(
                        skill.title,
                        diagramEdgePoints[index].x + xDelta,
                        diagramEdgePoints[index].y + yDelta,
                        paint
                    )
                }
            }
        }
    }

    private fun getDiagramFundamentalValues(skillsCount: Int, extraLinesCount: Int): List<List<Float>> {
        val fullLinesCount = extraLinesCount + 1
        val valuesLists = ArrayList<List<Float>>()
        var values: ArrayList<Float>
        val sectorWidthString: String = String.format("%.2f", 1f / fullLinesCount)
        val sectorWidth: Float = sectorWidthString.replace(",", ".").toFloat()

        for (index in fullLinesCount downTo 1) {
            values = ArrayList()

            for (i in 1..skillsCount) {
                values.add(sectorWidth * index)
            }
            valuesLists.add(values)
        }

        return valuesLists
    }

    private fun drawCenterPoint(canvas: Canvas) {
        canvas.drawCircle(diagramCenterPoint.x, diagramCenterPoint.y, 3f, paint)
    }

    private fun drawDiagram(canvas: Canvas) {
        val diagramPoints = getDiagramFundamentalValues(skills!!.size, 4)
        diagramPoints.forEachIndexed { index, points ->
            drawOneStep(points, canvas, isFirstStep = index == 0)
        }
    }

    private fun drawSkillValues(canvas: Canvas) {
        paint.color = ContextCompat.getColor(context, R.color.profile_chart_background)
        paint.style = Paint.Style.FILL
        path = Path()

        val skillsValues = skills!!.map {
            if (it.max != 0) {
                it.count.toFloat() / it.max
            } else {
                0f
            }
        }

        drawOneStep(skillsValues, canvas, isFirstStep = false)

        paint.color = ContextCompat.getColor(context, R.color.extra_accent_color)
        paint.style = Paint.Style.STROKE
        path = Path()

        drawOneStep(skillsValues, canvas, isFirstStep = false)
    }

    private fun drawOneStep(points: List<Float>, canvas: Canvas, isFirstStep: Boolean) {
        var angle: Float
        var xFactor: Float
        var yFactor: Float
        var x: Float
        var y: Float

        for ((i, value) in points.withIndex()) {
            Log.i("myLog", "point value = $value")
            angle = (2 * pi / points.size) * i - pi / 2
            xFactor = (diagramViewWidth - diagramViewWidth / 2.3f) * 0.54f * value
            yFactor = (diagramViewWidth - diagramViewWidth / 2.3f) * 0.55f * value

            x = cos(angle) * xFactor + diagramCenterPoint.x
            y = sin(angle) * yFactor + diagramCenterPoint.y

            if (i == 0) {
                startPoint = PointF(x, y)
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }

            if (isFirstStep) {
                diagramEdgePoints.add(PointF(x, y))
                canvas.drawLine(diagramCenterPoint.x, diagramCenterPoint.y, x, y, paint)
            }
        }

        path.lineTo(startPoint.x, startPoint.y)

        canvas.drawPath(path, paint)
    }
}