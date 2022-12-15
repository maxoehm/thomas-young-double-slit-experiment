package mx.utils

import mx.Properties.colors
import java.awt.Color
import kotlin.math.floor

object ColorUtils {

    /**
     * Generates color palette used by the simulation
     * Features only grey colors
     *
     * @param offset Dictates the brightness, the higher the value the brighter the colors
     * @return Array of grey tones
     */
    fun generateGradientHex(offset: Int): Array<Color?> {
        val colors = arrayOfNulls<Color>(256)
        val hexValues = charArrayOf('f', 'e', 'd', 'c', 'b', 'a', '9', '8', '7', '6', '5', '4', '3', '2', '1', '0')

        //second half from offset is black
        for (i in colors.size - 1 downTo colors.size - 1 + offset) {
            colors[i] = Color.decode("#000000")
        }

        var index = colors.size - 1 + offset
        hexValues.indices.reversed().flatMap { i ->
            val hexValue1 = hexValues[i]
            hexValues.indices.reversed().map { j ->
                val hexValue2 = hexValues[j]
                val color = "#$hexValue1$hexValue2$hexValue1$hexValue2$hexValue1$hexValue2"
                if (index >= 0) {
                    colors[index] = Color.decode(color)
                    index--
                }
                colors
            }
        }.firstOrNull()

        return colors
    }

}