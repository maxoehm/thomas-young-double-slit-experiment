package mx

import java.awt.Color

object Properties {

    var movementSpeed = 0.0
    var wavelength = 30
    var space = 400 //distance between slits
    var width = 2000
    private var graphicsHeight = 500 + 70
    var frameHeight = graphicsHeight + 280
    var height = frameHeight
    var leftCenter = intArrayOf((width - space) / 2, height)
    var rightCenter = intArrayOf((width - space) / 2 + space, height)
    var leftOrigin = intArrayOf(leftCenter[0] - 5, leftCenter[1] + 20)
    var rightOrigin = intArrayOf(rightCenter[0] - 5, rightCenter[1] + 20)
    var wavedecay = 400
    var colors: Array<Color?>? = arrayOfNulls(256)
    var pixelSize = 4

    /**
     *  Negative values represent darker color, positive values represent brighter color
     */
    var brightness = -25
    var frameWidth = 2000

}