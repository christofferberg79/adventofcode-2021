package cberg.aoc2021

class Day3(private val report: List<String>) {
    init {
        require(report.isNotEmpty())
        require(report.all { line -> line.length == report.first().length })
        require(report.all { line -> line.all { char -> char in setOf('0', '1') } })
    }

    private val indices = report.first().indices

    fun part1(): Int {
        val gamma = part1(::gammaSelector)
        val epsilon = part1(::epsilonSelector)
        return gamma * epsilon
    }

    private fun part1(selector: (List<Char>) -> Char) =
        indices.map { index -> selectChar(report, index, selector) }
            .joinToString(separator = "").toInt(2)

    private fun gammaSelector(chars: List<Char>) = chars.last()
    private fun epsilonSelector(chars: List<Char>) = chars.first()

    fun part2(): Int {
        val ogr = part2(::gammaSelector)
        val csr = part2(::epsilonSelector)
        return ogr * csr
    }

    private fun part2(selector: (List<Char>) -> Char) =
        filter(report, selector, 0).single().toInt(2)

    private tailrec fun filter(report: List<String>, selector: (List<Char>) -> Char, i: Int): List<String> {
        if (report.size == 1) {
            return report
        }
        val filterValue = selectChar(report, i, selector)
        val filtered = report.filter { line -> line[i] == filterValue }
        return filter(filtered, selector, i + 1)
    }

    private fun selectChar(report: List<String>, index: Int, selector: (List<Char>) -> Char): Char {
        val charsAtIndex = report.map { line -> line[index] }
        return selector(charsAtIndex.sortedByOccurrenceAndValue())
    }

    private fun List<Char>.sortedByOccurrenceAndValue() =
        groupingBy { it }.eachCount().toList().sortedWith(compareBy({ it.second }, { it.first })).map { it.first }
}
