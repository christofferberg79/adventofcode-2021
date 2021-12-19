package cberg.aoc2021

class Day8(private val input: List<String>) {
    fun part1() = input
        .flatMap { line -> line.split(Regex("[| ]+")).takeLast(4) }
        .count { value -> value.length in setOf(2, 4, 3, 7) }

    fun part2() = input.sumOf { line ->
        val values = line.split(Regex("[| ]+")).map { it.toSet() }
        val valueToDigit = mutableMapOf<Set<Char>, Int>()
        for (value in values) {
            when (value.size) {
                2 -> valueToDigit[value] = 1
                4 -> valueToDigit[value] = 4
                3 -> valueToDigit[value] = 7
                7 -> valueToDigit[value] = 8
            }
        }

        check(valueToDigit.values.containsAll(setOf(1, 4, 7, 8)))

        val valueSet4 = valueToDigit.filterValues { it == 4 }.keys.single()
        val valueSet7 = valueToDigit.filterValues { it == 7 }.keys.single()

        for (value in values) {
            when (value.size) {
                5 -> when {
                    (value - valueSet7).size == 2 -> valueToDigit[value] = 3
                    (value - valueSet4).size == 3 -> valueToDigit[value] = 2
                    (value - valueSet4).size == 2 -> valueToDigit[value] = 5
                }
                6 -> when {
                    (value - valueSet7).size == 4 -> valueToDigit[value] = 6
                    (value - valueSet4).size == 2 -> valueToDigit[value] = 9
                    (value - valueSet4).size == 3 -> valueToDigit[value] = 0
                }
            }
        }

        values.takeLast(4)
            .map { valueToDigit[it] ?: error("I don't know what $it means") }
            .joinToString("")
            .toInt()
    }
}
