package com.inkapplications.karps.structures.unit

import kotlin.test.Test
import kotlin.test.assertEquals

class TemperatureTest {
    @Test
    fun conversions() {
        assertEquals(0.degreesCelsius, 32.degreesFahrenheit)
        assertEquals((-40).degreesCelsius, (-40).degreesFahrenheit)
        assertEquals(25.degreesCelsius,77.degreesFahrenheit)

        assertEquals(32.degreesFahrenheit, 0.degreesCelsius)
        assertEquals(77.degreesFahrenheit,25.degreesCelsius)
        assertEquals((-40).degreesFahrenheit, (-40).degreesCelsius)

        assertEquals(32, 0.degreesCelsius.fahrenheit)
        assertEquals(0f, 32.degreesFahrenheit.celsius)

        assertEquals(68, 20.degreesCelsius.fahrenheit)
        assertEquals(22.22f, 72.degreesFahrenheit.celsius, .01f)

        assertEquals(-40, (-40).degreesCelsius.fahrenheit)
        assertEquals((-40f), (-40).degreesFahrenheit.celsius)
    }
}
