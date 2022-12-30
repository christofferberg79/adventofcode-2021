package cberg.aoc2021

class Day24 {

    fun part1() = solve(9 downTo 1)

    fun part2() = solve(1..9)

    private fun solve(digits: IntProgression): String {
        for (n0 in digits) {
            val z0 = n0 + 10L
            for (n1 in digits) {
                val z1 = z0 * 26 + n1 + 16
                for (n2 in digits) {
                    val z2 = z1 * 26 + n2
                    for (n3 in digits) {
                        val z3 = z2 * 26 + n3 + 13
                        val n4 = z3 % 26 - 14
                        if (n4 in 1..9) {
                            val z4 = z3 / 26
                            val n5 = z4 % 26 - 4
                            if (n5 in 1..9) {
                                val z5 = z4 / 26
                                for (n6 in digits) {
                                    val z6 = z5 * 26 + n6 + 11
                                    val n7 = z6 % 26 - 3
                                    if (n7 in 1..9) {
                                        val z7 = z6 / 26
                                        for (n8 in digits) {
                                            val z8 = z7 * 26 + n8 + 16
                                            val n9 = z8 % 26 - 12
                                            if (n9 in 1..9) {
                                                val z9 = z8 / 26
                                                for (n10 in digits) {
                                                    val z10 = z9 * 26 + n10 + 15
                                                    val n11 = z10 % 26 - 12
                                                    if (n11 in 1..9) {
                                                        val z11 = z10 / 26
                                                        val n12 = z11 % 26 - 15
                                                        if (n12 in 1..9) {
                                                            val z12 = z11 / 26
                                                            val n13 = z12 % 26 - 12
                                                            if (n13 in 1..9) {
                                                                val z13 = z12 / 26
                                                                if (z13 == 0L) {
                                                                    return "$n0$n1$n2$n3$n4$n5$n6$n7$n8$n9$n10$n11$n12$n13"
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        error("no solution found")
    }

    class Alu(private val instructions: List<String>) {
        constructor(vararg instructions: String) : this(instructions.toList())

        private val variables = mutableMapOf('w' to 0L, 'x' to 0L, 'y' to 0L, 'z' to 0L)

        val w: Long get() = variables['w'] ?: 0L
        val x: Long get() = variables['x'] ?: 0L
        val y: Long get() = variables['y'] ?: 0L
        val z: Long get() = variables['z'] ?: 0L

        fun run(input: String = "", w0: Long = 0, x0: Long = 0, y0: Long = 0, z0: Long = 0) {
            variables['w'] = w0
            variables['x'] = x0
            variables['y'] = y0
            variables['z'] = z0

//            println("initial state -> $this")
//            println("input: $input")
            var nextInput = 0
            for (ins in instructions) {
                val parts = ins.split(" ")
                when (parts[0]) {
                    "inp" -> {
                        if (nextInput > input.lastIndex) {
                            return
                        }
                        set(parts[1], input[nextInput++].digitToInt().toLong())
                    }

                    "add" -> set(parts[1], get(parts[1]) + get(parts[2]))
                    "mul" -> set(parts[1], get(parts[1]) * get(parts[2]))
                    "div" -> set(parts[1], get(parts[1]) / get(parts[2]))
                    "mod" -> set(parts[1], get(parts[1]) % get(parts[2]))
                    "eql" -> set(parts[1], if (get(parts[1]) == get(parts[2])) 1 else 0)
                }
//                println("${index + 1}: $ins -> $this")
            }
//            println("final state -> $this")
        }

        override fun toString() = "w: $w, x: $x, y: $y, z: $z"

        private fun set(variable: String, value: Long) {
            variables[variable.single()] = value
        }

        private fun get(variable: String): Long {
            return variable.toLongOrNull() ?: variables[variable.single()]!!
        }
    }
}
