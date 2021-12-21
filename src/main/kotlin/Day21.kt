package cberg.aoc2021

import kotlin.math.max

class Day21(private val input: List<String>) {
    fun part1(): Int {
        val positions = input.map { it.substringAfter(": ").toInt() }.toIntArray()
        val scores = IntArray(positions.size)
        var player = positions.lastIndex
        var rolls = 0

        while (scores[player] < 1000) {
            player = (player + 1) % positions.size
            val moves = generateSequence { rolls++ % 100 + 1 }.take(3).sum()
            positions[player] = (positions[player] + moves - 1) % 10 + 1
            scores[player] += positions[player]
        }
        return scores.minOrNull()!! * rolls
    }

    fun part2(): Long {
        val startState = input.map { it.substringAfter(": ").toInt() }
            .let { (pos1, pos2) -> State(Player(pos1, 0), Player(pos2, 0), true, 1) }

        val finalStats = getStatsFor(startState, mutableMapOf())
        return max(finalStats.player1, finalStats.player2)
    }

    private fun getStatsFor(state: State, stats: MutableMap<State, Wins>): Wins = when {
        state.player1.hasWon() -> Wins(1, 0)
        state.player2.hasWon() -> Wins(0, 1)
        else -> {
            stats.getOrPut(state) {
                state.update()
                    .map { (newState, count) -> getStatsFor(newState, stats) * count }
                    .reduce { acc, wins -> acc + wins }
            }
        }
    }

    data class Wins(val player1: Long, val player2: Long)

    private operator fun Wins.times(n: Int) = Wins(player1 * n, player2 * n)
    private operator fun Wins.plus(other: Wins) = Wins(player1 + other.player1, player2 + other.player2)

    data class Player(val position: Int, val score: Int)

    private fun Player.hasWon() = score >= 21

    private fun Player.move(roll: Int): Player {
        val newPosition = (position + roll - 1) % 10 + 1
        return Player(newPosition, score + newPosition)
    }

    data class State(val player1: Player, val player2: Player, val player1sTurn: Boolean, val turn: Int)

    private fun State.update(): List<Pair<State, Int>> {
        return rolls.map { (roll, count) -> update(roll) to count }
    }

    private val rolls = (1..3).flatMap { r1 -> (1..3).flatMap { r2 -> (1..3).map { r3 -> r1 + r2 + r3 } } }
        .groupingBy { it }.eachCount()

    private fun State.update(roll: Int) = when (player1sTurn) {
        true -> State(player1.move(roll), player2, false, turn + 1)
        false -> State(player1, player2.move(roll), true, turn + 1)
    }
}
