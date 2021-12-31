package cberg.aoc2021

class Day25(input: List<String>) {
    private val lastX = input.first().lastIndex
    private val lastY = input.lastIndex
    private val initialState = input.flatMapIndexed { y: Int, s: String ->
        s.mapIndexedNotNull { x, c -> if (c == '.') null else Pos(x, y) to c }
    }.toMap()

    fun part1() = generateSequence(initialState) { state ->
        val nextState = state.update()
        if (nextState == state) {
            null
        } else {
            nextState
        }
    }.count()

    fun part2() = 0

    data class Pos(val x: Int, val y: Int)

    private fun Map<Pos, Char>.update() = update('>', 1, 0).update('v', 0, 1)
    private fun Map<Pos, Char>.update(c: Char, dx: Int, dy: Int) = mapKeys { (pos, v) ->
        if (v == c) {
            val newPos = Pos(
                (pos.x + dx).let { x -> if (x > lastX) 0 else if (x < 0) lastX else x },
                (pos.y + dy).let { y -> if (y > lastY) 0 else if (y < 0) lastY else y }
            )
            if (newPos in this) {
                pos
            } else {
                newPos
            }
        } else {
            pos
        }
    }
}
