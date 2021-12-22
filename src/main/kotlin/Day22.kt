package cberg.aoc2021

import kotlin.math.max
import kotlin.math.min

class Day22(input: List<String>) {

    private val instructions = parse(input)

    private val boundary = Box(-50L..50L, -50L..50L, -50L..50L)

    fun part1() = instructions.fold(State()) { state, inst ->
        val box = Box(inst.xs, inst.ys, inst.zs) intersect boundary
        if (box == null) {
            state
        } else if (inst.on) {
            state + box
        } else {
            state - box
        }
    }.size

    fun part2() = instructions.fold(State()) { state, inst ->
        val box = Box(inst.xs, inst.ys, inst.zs)
        if (inst.on) {
            state + box
        } else {
            state - box
        }
    }.size

    private fun parse(input: List<String>): List<Instruction> {
        val regex = Regex("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)")
        return input.map { line ->
            regex.matchEntire(line)?.destructured?.let { (onOrOff, x1, x2, y1, y2, z1, z2) ->
                Instruction(
                    onOrOff == "on",
                    x1.toLong()..x2.toLong(),
                    y1.toLong()..y2.toLong(),
                    z1.toLong()..z2.toLong()
                )
            } ?: error("Invalid input: $line")
        }
    }

    private data class Vec3(val x: Long, val y: Long, val z: Long)

    private data class Instruction(val on: Boolean, val xs: LongRange, val ys: LongRange, val zs: LongRange)

    private data class Box(val min: Vec3, val max: Vec3) {
        constructor(xs: LongRange, ys: LongRange, zs: LongRange) : this(
            Vec3(xs.first, ys.first, zs.first),
            Vec3(xs.last, ys.last, zs.last)
        )

        val size get() = (max.x - min.x + 1) * (max.y - min.y + 1) * (max.z - min.z + 1)

        infix fun intersect(o: Box): Box? {
            val minV = Vec3(max(min.x, o.min.x), max(min.y, o.min.y), max(min.z, o.min.z))
            val maxV = Vec3(min(max.x, o.max.x), min(max.y, o.max.y), min(max.z, o.max.z))
            return if (minV.x <= maxV.x && minV.y <= maxV.y && minV.z <= maxV.z) {
                Box(minV, maxV)
            } else {
                null
            }
        }
    }

    private class State(
        private val pos: List<Box> = listOf(),
        private val neg: List<Box> = listOf()
    ) {
        val size get() = pos.sumOf { it.size } - neg.sumOf { it.size }

        operator fun plus(box: Box) = State(
            pos + neg.mapNotNull { it intersect box } + box,
            neg + pos.mapNotNull { it intersect box }
        )

        operator fun minus(box: Box) = State(
            pos + neg.mapNotNull { it intersect box },
            neg + pos.mapNotNull { it intersect box }
        )
    }
}
