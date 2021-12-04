package cberg.aoc2021

class Day3(private val report: List<String>) {
    constructor() : this(Input("day3.txt").lines())

    fun part1(): Int {
        val count = report.first().indices.map { i ->
            report.map { line -> line[i] }.groupingBy { char -> char }.eachCount()
        }

        val gamma = count.map { it.maxByOrNull { (_, count) -> count }?.key ?: error("invalid input") }
            .joinToString(separator = "").toInt(2)

        val epsilon = count.map { it.minByOrNull { (_, count) -> count }?.key ?: error("invalid input") }
            .joinToString(separator = "").toInt(2)

        return gamma * epsilon
    }

    fun part2(): Int {
        val indices = report.first().indices

        var work = report
        for (i in indices) {
            val count = indices.map { i ->
                work.map { line -> line[i] }.groupingBy { char -> char }.eachCount()
            }
            val ones = count[i].getOrDefault('1', 0)
            val zeros = count[i].getOrDefault('0', 0)
            val filterValue = if (ones >= zeros) '1' else '0'
            work = work.filter { line -> line[i] == filterValue }
            if (work.size == 1) {
                break
            }
        }
        val ogr = work.first().toInt(2)

        work = report
        for (i in indices) {
            val count = indices.map { i ->
                work.map { line -> line[i] }.groupingBy { char -> char }.eachCount()
            }
            val ones = count[i].getOrDefault('1', 0)
            val zeros = count[i].getOrDefault('0', 0)
            val filterValue = if (ones >= zeros) '0' else '1'
            work = work.filter { line -> line[i] == filterValue }
            if (work.size == 1) {
                break
            }
        }
        val csr = work.first().toInt(2)

        return ogr * csr
    }
}
