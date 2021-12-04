package cberg.aoc2021

import java.io.File

class Input(private val filename: String) {
    fun lines() = File("input/$filename").readLines()
    fun intLines() = lines().map { it.toInt() }
}