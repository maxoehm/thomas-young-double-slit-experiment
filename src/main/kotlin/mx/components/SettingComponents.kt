package mx.components

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.event.ChangeListener

object SettingComponents {

    fun whiteSpace(): JPanel {
        val whiteSpace = JPanel(FlowLayout())
        whiteSpace.isOpaque = true
        whiteSpace.preferredSize = Dimension(200, 10)
        return whiteSpace
    }

    fun newLabel(text: String?): JLabel {
        val label = JLabel(text)
        label.horizontalAlignment = JLabel.CENTER
        label.font = Font("Serif", Font.PLAIN, 18)
        return label
    }

    fun newProperty(
        value: Int,
        min: Int,
        max: Int,
        majorSpacing: Int,
        minorSpacing: Int,
        changeListener: ChangeListener?
    ): JSlider {
        val slider = JSlider()
        slider.paintTrack = true
        slider.paintTicks = true
        slider.paintLabels = true
        slider.minimum = min
        slider.maximum = max
        slider.value = value
        slider.majorTickSpacing = majorSpacing
        slider.minorTickSpacing = minorSpacing
        slider.addChangeListener(changeListener)
        return slider
    }

}