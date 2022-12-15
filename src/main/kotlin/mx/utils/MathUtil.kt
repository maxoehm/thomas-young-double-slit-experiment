package mx.utils

import mx.Properties
import mx.Properties.wavedecay
import mx.Properties.wavelength
import kotlin.math.*

object MathUtil {

    fun getSlitColorLevel(x: Int, y: Int, slitCoords: IntArray?): Double = with(slitCoords!!) {
        val space = getDistance(x, y, get(0), get(1))
        val theta = getTheta(x, y, get(0), get(1))

        val waveLevel = (cos(Math.PI * space / wavelength + Properties.movementSpeed) + 1) / 2
        val spaceLevel = sqrt(0.0.coerceAtLeast(wavedecay - space) / wavedecay)

        abs(waveLevel * sin(theta) * spaceLevel)
    }


    private fun getDistance(x1: Int, y1: Int, x2: Int, y2: Int): Double {
        return sqrt((x1 - x2).toDouble().pow(2.0) + (y1 - y2).toDouble().pow(2.0))
    }

    private fun getTheta(x: Int, y: Int, x2: Int, y2: Int): Double {
        return abs(acos(((x - x2).toDouble() / getDistance(x, y, x2, y2))))
    }

}