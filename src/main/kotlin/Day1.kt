package cberg.aoc2021

class Day1 {
    fun part1(measurements: List<Int>) = measurements.zipWithNext().count { (m1, m2) -> m2 > m1 }
}