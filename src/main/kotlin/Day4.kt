package cberg.aoc2021

class Day4(input: List<String>) {
    private val numbers = parseNumbers(input)
    private val boards = parseBoards(input)

    fun part1(): Int {
        for (number in numbers) {
            for (board in boards) {
                board.mark(number)
                if (board.hasWon()) {
                    return board.calculateScore(number)
                }
            }
        }
        error("No winner")
    }

    fun part2(): Int {
        numbers.fold(boards) { boardsLeft, number ->
            boardsLeft.forEach { board -> board.mark(number) }
            boardsLeft.singleOrNull()?.let { board ->
                if (board.hasWon()) {
                    return board.calculateScore(number)
                }
            }
            boardsLeft.filter { board -> !board.hasWon() }
        }
        error("No winner")
    }

    private class Board(numbers: List<List<Int>>) {
        private var data = numbers.map { row -> row.map { number -> number to false } }

        fun mark(number: Int) {
            data = data.map { row ->
                row.map { cell -> if (cell.first == number) cell.copy(second = true) else cell }
            }
        }

        fun hasWon() = hasWonByRow() || hasWonByCol()

        private fun hasWonByRow() = data.any { row -> row.all { (_, mark) -> mark } }

        private fun hasWonByCol() = (0..4).any { col -> data.all { row -> row[col].second } }

        fun calculateScore(number: Int) =
            data.sumOf { row -> row.sumOf { (num, mark) -> if (mark) 0 else num } } * number

        init {
            require(numbers.size == 5)
            require(numbers.all { row -> row.size == 5 })
        }
    }

    private fun parseNumbers(input: List<String>) = input.first().split(",").map { it.toInt() }

    private fun parseBoards(input: List<String>) = input.drop(1).chunked(6).map { chunk ->
        chunk.drop(1).map { row ->
            val split = row.trim().split(Regex("\\s+"))
            split.map { s -> s.toInt() }
        }.let { Board(it) }
    }
}
