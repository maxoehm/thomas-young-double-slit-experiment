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
        colors = generateGradientHex(brightness)
        animationFrame()
        settingsFrame()
        height = frameHeight
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
        val brightnessLabel = newLabel("Brightness: " + (Properties.brightness + 25))
        val distanceLabel = newLabel("Space between origins: $space")
        val wavelifetimeLabel = newLabel("Wave decay: $wavedecay")
        val wavelengthLabel = newLabel("Wavelength: $wavelength")

        val brightnessSlider = newProperty(0, -25, 25, 10, 5) { e ->
            val property = e.source as JSlider
            brightness = -25 + property.value
            colors = generateGradientHex(brightness)
            brightnessLabel.text = "Brightness: " + (brightness + 25)
        }

        settingsProperty.add(whiteSpace())
        settingsProperty.add(brightnessLabel)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(brightnessSlider)

        val spaceSlider = newProperty(space, 0, 1000, 250, 125) { e ->
            val property = e.source as JSlider
            space = property.value
            distanceLabel.text = "Space between origins: $space"
            leftCenter = intArrayOf((width - space) / 2, height)
            rightCenter = intArrayOf((width - space) / 2 + space, height)
            leftOrigin = intArrayOf(leftCenter[0] - 5, leftCenter[1] + 20)
            rightOrigin = intArrayOf(rightCenter[0] - 5, rightCenter[1] + 20)
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(distanceLabel)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(spaceSlider)

        val lengthToWallSlider = newProperty(wavedecay, 50, 1000, 250, 125) { e ->
            val property = e.source as JSlider
            wavedecay = property.value
            wavelifetimeLabel.text = "Wave decay: $wavedecay"
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(wavelifetimeLabel)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(lengthToWallSlider)

        val wavelengthSlider = newProperty(wavelength, 0, 100, 10, 5) { e ->
            val property = e.source as JSlider
            wavelength = property.value
            wavelengthLabel.text = "Wavelength: $wavelength"
        }
        settingsProperty.add(whiteSpace())
        settingsProperty.add(wavelengthLabel)
        settingsProperty.add(whiteSpace())
        settingsProperty.add(wavelengthSlider)

        settingsFrame.add(settingsProperty)
        settingsFrame.pack()
        settingsFrame.isVisible = true
    }

    private fun animationFrame() {
        val animationFrame = JFrame("Thomas Young - Doppelspaltexperiment")
        animationFrame.preferredSize = Dimension(Properties.frameWidth, frameHeight)
        animationFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        animationFrame.contentPane.background = Color.BLACK
        val frameLayout = FlowLayout()
        frameLayout.vgap = 0
        animationFrame.layout = frameLayout

        val graphics = Simulation()

        animationFrame.add(graphics)
        animationFrame.pack()
        animationFrame.isVisible = true
    }


}