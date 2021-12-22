package cberg.aoc2021

import kotlin.math.max
import kotlin.math.min

class Day22(input: List<String>) {

    private val instructions = parse(input)

    private val boundary = Box(-50..50, -50..50, -50..50)

    fun part1() = instructions.fold(emptySet<Vec3>()) { set, inst ->
        (Box(inst.xs, inst.ys, inst.zs) intersect boundary)
            ?.let { box ->
                if (inst.on) {
                    set + box.toSet()
                } else {
                    set - box.toSet()
                }
            }
            ?: set
    }.size

    fun part2() = 0

    private fun parse(input: List<String>): List<Instruction> {
        val regex = Regex("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)")
        return input.map { line ->
            regex.matchEntire(line)?.destructured?.let { (onOrOff, x1, x2, y1, y2, z1, z2) ->
                Instruction(
                    onOrOff == "on",
                    x1.toInt()..x2.toInt(),
                    y1.toInt()..y2.toInt(),
                    z1.toInt()..z2.toInt()
                )
            } ?: error("Invalid input: $line")
        }
    }

    private data class Vec3(val x: Int, val y: Int, val z: Int)
    private data class Box(val min: Vec3, val max: Vec3)

    private data class Instruction(val on: Boolean, val xs: IntRange, val ys: IntRange, val zs: IntRange)

    private infix fun Box.intersect(o: Box): Box? {
        val minV = Vec3(max(min.x, o.min.x), max(min.y, o.min.y), max(min.z, o.min.z))
        val maxV = Vec3(min(max.x, o.max.x), min(max.y, o.max.y), min(max.z, o.max.z))
        return if (minV.x < maxV.x && minV.y < maxV.y && minV.z < maxV.z) {
            Box(minV, maxV)
        } else {
            null
        }
    }

    private fun Box(xs: IntRange, ys: IntRange, zs: IntRange) =
        Box(Vec3(xs.first, ys.first, zs.first), Vec3(xs.last, ys.last, zs.last))

    private fun Box.toSet() = (min.x..max.x).flatMap { x ->
        (min.y..max.y).flatMap { y ->
            (min.z..max.z).map { z -> Vec3(x, y, z) }
        }
    }.toSet()
}
