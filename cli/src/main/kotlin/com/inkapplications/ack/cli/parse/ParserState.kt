@file:OptIn(ExperimentalTime::class)
package com.inkapplications.ack.cli.parse

import com.inkapplications.ack.structures.PacketData
import java.io.File
import kotlin.math.min
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

sealed interface ParserState {
    val testFile: File
    data class Initializing(override val testFile: File): ParserState
    data class Running(override val testFile: File, val complete: Int, val total: Int): ParserState

    data class Complete(override val testFile: File, val tests: List<LineStatus>, val time: Duration): ParserState {
        val passed = tests.filterIsInstance<LineStatus.Passed>().count()
        val failed = tests.filterIsInstance<LineStatus.Failed>().count()
        val unknown = tests.filterIsInstance<LineStatus.Passed>().filter { it.packet.data is PacketData.Unknown }.count()
        val unidentifiedIdentifiers = tests.filterIsInstance<LineStatus.Passed>()
            .map { it.packet }
            .filterIsInstance<PacketData.Unknown>()
            .groupBy { it.body[0] }
            .map {
                it.key to it.value.count()
            }
            .sortedByDescending { it.second }
        val topUnidentified = unidentifiedIdentifiers.subList(0, min(5, unidentifiedIdentifiers.size))
    }
}
