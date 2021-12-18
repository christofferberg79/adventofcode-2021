package cberg.aoc2021

class SnailfishNumber private constructor(private val node: Node) {
    constructor(s: String) : this(Parser(s).parse().reduce())

    override fun toString() = node.toString()

    val magnitude get() = node.magnitude

    operator fun plus(other: SnailfishNumber): SnailfishNumber {
        return SnailfishNumber(PairNode(this.node, other.node).reduce())
    }
}

private sealed interface Node {
    val magnitude: Int
    fun explode(depth: Int): ExplosionResult? = null
    fun split(): Node?
    fun addLeft(n: Int): Node
    fun addRight(n: Int): Node
}

private fun Node.reduce() = generateSequence(this) {
    it.explode(0)?.newNode ?: it.split()
}.last()

private data class PairNode(val left: Node, val right: Node) : Node {
    override val magnitude get() = 3 * left.magnitude + 2 * right.magnitude
    override fun toString() = "[$left,$right]"

    override fun explode(depth: Int): ExplosionResult? {
        if (depth == 4) {
            check(left is ValueNode && right is ValueNode) { "The tree is to deep" }
            return ExplosionResult(ValueNode(0), left.value, right.value)
        } else {
            left.explode(depth + 1)?.let { result ->
                val newLeft = result.newNode
                val newRight = result.addRight?.let { n -> right.addLeft(n) } ?: right
                return ExplosionResult(
                    newNode = PairNode(newLeft, newRight),
                    addLeft = result.addLeft
                )
            }
            right.explode(depth + 1)?.let { result ->
                val newLeft = result.addLeft?.let { n -> left.addRight(n) } ?: left
                val newRight = result.newNode
                return ExplosionResult(
                    newNode = PairNode(newLeft, newRight),
                    addRight = result.addRight
                )
            }
            return null
        }
    }

    override fun split(): Node? {
        left.split()?.let { newLeft ->
            return PairNode(newLeft, right)
        }
        right.split()?.let { newRight ->
            return PairNode(left, newRight)
        }
        return null
    }

    override fun addLeft(n: Int): Node {
        return PairNode(left.addLeft(n), right)
    }

    override fun addRight(n: Int): Node {
        return PairNode(left, right.addRight(n))
    }
}

private data class ValueNode(val value: Int) : Node {
    override val magnitude = value
    override fun addLeft(n: Int) = add(n)
    override fun addRight(n: Int) = add(n)

    private fun add(n: Int) = ValueNode(n + value)

    override fun split(): Node? {
        return if (value >= 10) {
            PairNode(ValueNode(value / 2), ValueNode(value - value / 2))
        } else {
            null
        }
    }

    override fun toString() = "$value"
}

private class ExplosionResult(val newNode: Node, val addLeft: Int? = null, val addRight: Int? = null)

private class Parser(private val s: String) {
    private var pos = 0
    fun parse() = when (s[pos]) {
        '[' -> parsePair()
        else -> readValue()
    }

    private fun parsePair(): Node {
        check(s[pos++] == '[') { "Expected [ at position ${pos - 1}" }
        val left = parse()
        check(s[pos++] == ',') { "Expected , at position ${pos - 1}" }
        val right = parse()
        check(s[pos++] == ']') { "Expected ] at position ${pos - 1}" }
        return PairNode(left, right)
    }

    private fun readValue(): Node {
        var value = 0
        while (s[pos].isDigit()) {
            value = value * 10 + s[pos++].digitToInt()
        }
        return ValueNode(value)
    }
}
