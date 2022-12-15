package mx

import mx.Properties.colors
import mx.Properties.leftOrigin
import mx.Properties.leftCenter
import mx.Properties.pixelSize
import mx.Properties.rightOrigin
import mx.Properties.rightCenter
import mx.utils.MathUtil
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent
import javax.swing.Timer
import kotlin.math.*

class Simulation : JComponent(), ActionListener {
    private var timer = Timer(100, this)

    init {
        preferredSize = Dimension(Properties.width, Properties.height + 100)
    }

    override fun actionPerformed(e: ActionEvent) {
        repaint()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        g.color = Color.DARK_GRAY
        g.fillRect(leftOrigin[0], leftOrigin[1],0 ,0)
        g.fillRect(rightOrigin[0], rightOrigin[1], 0, 0)
        Properties.movementSpeed -= 0.4

        drawPattern(g)
        timer.start()
    }

    private fun drawPattern(g: Graphics) {
        run {
            var x = 0
            while (x <= width) {
                run {
                    var y = 0
                    while (y <= height) {
                        g.color = getColor(x, y)
                        g.fillRect(x, y, pixelSize, pixelSize)
                        y += pixelSize
                    }
                }
                x += pixelSize
            }
        }
    }

    private fun getColor(x: Int, y: Int): Color? {
        return getGradientColor((
                MathUtil.getSlitColorLevel(x, y, leftCenter) +
                    MathUtil.getSlitColorLevel(x, y, rightCenter)) / 2)
    }

    private fun getGradientColor(colorLevel: Double): Color? {
        return colors!![colors!!.size - floor(colorLevel * colors!!.size).toInt() - 1]
    }


}