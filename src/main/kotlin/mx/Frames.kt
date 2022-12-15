package mx

import mx.Properties.brightness
import mx.Properties.colors
import mx.Properties.frameHeight
import mx.Properties.height
import mx.Properties.leftOrigin
import mx.Properties.leftCenter
import mx.Properties.rightOrigin
import mx.Properties.rightCenter
import mx.Properties.space
import mx.Properties.wavedecay
import mx.Properties.wavelength
import mx.Properties.width
import mx.components.SettingComponents.newLabel
import mx.components.SettingComponents.newProperty
import mx.components.SettingComponents.whiteSpace
import mx.utils.ColorUtils.generateGradientHex
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSlider

object Frames {

    @JvmStatic
    fun run() {
        colors = generateGradientHex(Properties.brightness)
        animationFrame()
        settingsFrame()
        height = frameHeight
    }

    private fun animationFrame() {
        val frame = JFrame("Thomas Young - Doppelspaltexperiment")
        frame.preferredSize = Dimension(Properties.frameWidth, frameHeight)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.background = Color.BLACK
        val frameLayout = FlowLayout()
        frameLayout.vgap = 0
        frame.layout = frameLayout

        val graphics = Simulation()

        frame.add(graphics)
        frame.pack()
        frame.isVisible = true
    }

    private fun settingsFrame() {
        val settingsFrame = JFrame("Settings")
        settingsFrame.preferredSize = Dimension(300, 600)
        settingsFrame.defaultCloseOperation = JFrame.HIDE_ON_CLOSE
        val frameLayoutSettings = FlowLayout()
        settingsFrame.layout = frameLayoutSettings

        val slidersLayout = GridLayout(18, 1)
        val settingsProperty = JPanel(slidersLayout)
        settingsProperty.isOpaque = true
        settingsProperty.preferredSize = Dimension(300, 600)

        //TEXTS
        val brightnessTxt = newLabel("Brightness: " + (Properties.brightness + 25))
        val distanceTxt = newLabel("Space between origins: $space")
        val lengthToWallTxt = newLabel("Wave decay: $wavedecay")
        val wavelengthTxt = newLabel("Wavelength: $wavelength")

        val brightnessSlider = newProperty(0, -25, 25, 10, 5) { e ->
            val property = e.source as JSlider
            brightness = -25 + property.value
            colors = generateGradientHex(brightness)
            brightnessTxt.text = "Brightness: " + (brightness + 25)
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(brightnessTxt)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(brightnessSlider)

        val spaceSlider = newProperty(space, 0, 1000, 250, 125) { e ->
            val property = e.source as JSlider
            space = property.value
            distanceTxt.text = "Space between origins: $space"
            leftCenter = intArrayOf((width - space) / 2, height)
            rightCenter = intArrayOf((width - space) / 2 + space, height)
            leftOrigin = intArrayOf(leftCenter[0] - 5, leftCenter[1] + 20)
            rightOrigin = intArrayOf(rightCenter[0] - 5, rightCenter[1] + 20)
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(distanceTxt)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(spaceSlider)

        val lengthToWallSlider = newProperty(wavedecay, 50, 1000, 250, 125) { e ->
            val property = e.source as JSlider
            wavedecay = property.value
            lengthToWallTxt.text = "Wave decay: $wavedecay"
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(lengthToWallTxt)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(lengthToWallSlider)

        val wavelengthSlider = newProperty(wavelength, 0, 100, 10, 5) { e ->
            val property = e.source as JSlider
            wavelength = property.value
            wavelengthTxt.text = "Wavelength: $wavelength"
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(wavelengthTxt)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(wavelengthSlider)

        settingsFrame.add(settingsProperty)
        settingsFrame.pack()
        settingsFrame.isVisible = true
    }

}