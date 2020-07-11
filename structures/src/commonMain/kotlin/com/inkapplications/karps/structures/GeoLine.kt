package com.inkapplications.karps.structures

import kotlin.math.abs
import kotlin.math.truncate

/**
 * A Latitude/Longitude line.
 *
 * This can be expressed as a decimal as part of Lat/Lng lookups
 * or as degrees/minutes/seconds and a Cardinal.
 */
sealed class GeoLine {
    abstract val degrees: Int
    abstract val minutes: Int
    abstract val seconds: Double
    abstract val cardinal: Cardinal
    val decimal: Double get() = (degrees.toDouble() + (minutes.toDouble() / 60.0) + (seconds / 3600.0)) * cardinal.decimalSign

    override fun toString(): String = "${degrees}º$minutes'$seconds\"${cardinal.symbol}"
}

/**
 * Latitude/parallel position of a coordinate.
 */
data class Latitude(
    override val degrees: Int,
    override val minutes: Int,
    override val seconds: Double,
    override val cardinal: Cardinal
): GeoLine() {
    constructor(decimal: Double): this(
        degrees = decimal.degrees,
        minutes = decimal.minutes,
        seconds = decimal.seconds,
        cardinal = if (decimal < 0) Cardinal.South else Cardinal.North
    )
}

/**
 * Longitude/meridian position of a coordinate.
 */
data class Longitude(
    override val degrees: Int,
    override val minutes: Int,
    override val seconds: Double,
    override val cardinal: Cardinal
): GeoLine() {
    constructor(decimal: Double): this(
        degrees = decimal.degrees,
        minutes = decimal.minutes,
        seconds = decimal.seconds,
        cardinal = if (decimal < 0) Cardinal.West else Cardinal.East
    )
}

private val Double.degrees: Int get() = truncate(abs(this)).toInt()
private val Double.minutes: Int get() = truncate((this - degrees) * 60).toInt()
private val Double.seconds: Double get() = truncate((this - degrees - minutes) * 60)

