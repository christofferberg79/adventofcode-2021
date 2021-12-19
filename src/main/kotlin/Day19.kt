package cberg.aoc2021

import kotlin.math.abs

class Day19(private val input: List<String>) {
    fun part1(): Int {
        val scanners = parse(input)
        val allBeacons = scanners[0].toMutableSet()
        val lhss = mutableListOf(scanners[0])
        val rhss = scanners.drop(1).toMutableList()
        while (lhss.isNotEmpty() && rhss.isNotEmpty()) {
            val lhs = lhss.removeFirst()
            val iter = rhss.listIterator()
            while (iter.hasNext()) {
                val rhs = iter.next()
                val rhsInLhsSpace = findOverlap(lhs, rhs)
                if (rhsInLhsSpace != null) {
                    iter.remove()
                    lhss += rhsInLhsSpace.first
                    allBeacons.addAll(rhsInLhsSpace.first)
                }
            }
        }
        return allBeacons.size
    }

    fun part2(): Int {
        val scanners = parse(input)
        val lhss = mutableListOf(scanners[0])
        val rhss = scanners.drop(1).toMutableList()
        val translations = mutableListOf<Vec3>()
        while (lhss.isNotEmpty() && rhss.isNotEmpty()) {
            val lhs = lhss.removeFirst()
            val iter = rhss.listIterator()
            while (iter.hasNext()) {
                val rhs = iter.next()
                val rhsInLhsSpace = findOverlap(lhs, rhs)
                if (rhsInLhsSpace != null) {
                    iter.remove()
                    lhss += rhsInLhsSpace.first
                    translations += rhsInLhsSpace.second
                }
            }
        }

        return translations.flatMap { t1 -> translations.map { t2 -> (t2 - t1).manhattanDistance } }.maxOrNull()!!
    }

    private fun parse(input: List<String>): List<Set<Vec3>> {
        val scanners = mutableListOf<Set<Vec3>>()
        lateinit var beacons: MutableSet<Vec3>
        for (line in input.filterNot { it.isBlank() }) {
            if (line.contains("scanner")) {
                beacons = mutableSetOf()
                scanners += beacons
            } else {
                val (x, y, z) = line.split(",").map { it.toInt() }
                beacons += Vec3(x, y, z)
            }
        }
        return scanners
    }

    private fun findOverlap(scanner1: Set<Vec3>, scanner2: Set<Vec3>): Pair<Set<Vec3>, Vec3>? {
        for (rotation in rotations) {
            val scanner2Rotated = scanner2.map { vec -> rotation * vec }
            for (b1 in scanner1) {
                for (b2 in scanner2Rotated) {
                    val translation = b1 - b2
                    val count = scanner2Rotated.count { it + translation in scanner1 }
                    if (count >= 12) {
                        return Pair(scanner2Rotated.map { it + translation }.toSet(), translation)
                    }
                }
            }
        }
        return null
    }
}

data class Vec3(val x: Int, val y: Int, val z: Int)

operator fun Vec3.plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)
operator fun Vec3.minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)
operator fun Vec3.unaryMinus() = Vec3(-x, -y, -z)

private val Vec3.manhattanDistance get() = abs(x) + abs(y) + abs(z)

data class Mat3(val v1: Vec3, val v2: Vec3, val v3: Vec3)

operator fun Mat3.times(vec: Vec3) = Vec3(
    vec.x * v1.x + vec.y * v2.x + vec.z * v3.x,
    vec.x * v1.y + vec.y * v2.y + vec.z * v3.y,
    vec.x * v1.z + vec.y * v2.z + vec.z * v3.z
)

val vx = Vec3(1, 0, 0)
val vy = Vec3(0, 1, 0)
val vz = Vec3(0, 0, 1)

val rotations = listOf(
    Mat3(vx, vy, vz),
    Mat3(vx, vz, -vy),
    Mat3(vx, -vy, -vz),
    Mat3(vx, -vz, vy),

    Mat3(-vx, -vy, vz),
    Mat3(-vx, vz, vy),
    Mat3(-vx, vy, -vz),
    Mat3(-vx, -vz, -vy),

    Mat3(vy, vz, vx),
    Mat3(vy, vx, -vz),
    Mat3(vy, -vz, -vx),
    Mat3(vy, -vx, vz),

    Mat3(-vy, -vz, vx),
    Mat3(-vy, vx, vz),
    Mat3(-vy, vz, -vx),
    Mat3(-vy, -vx, -vz),

    Mat3(vz, vx, vy),
    Mat3(vz, vy, -vx),
    Mat3(vz, -vx, -vy),
    Mat3(vz, -vy, vx),

    Mat3(-vz, -vy, -vx),
    Mat3(-vz, -vx, vy),
    Mat3(-vz, vy, vx),
    Mat3(-vz, vx, -vy),
)
