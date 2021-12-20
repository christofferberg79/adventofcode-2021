package cberg.aoc2021

class Day20(input: List<String>) {
    private val algorithm = input.first()
    private val original = Image(input.drop(2).flatMapIndexed { y: Int, line: String ->
        line.mapIndexedNotNull { x, c -> Pixel(x, y) to c }
    }.toMap())

    init {
        check(algorithm.length == 512)
    }

    fun part1() = solve(2)

    fun part2() = solve(50)

    private fun solve(enhancements: Int) = generateSequence(original) { image -> enhance(image) }
        .elementAt(enhancements).let { image ->
            check(image.default == '.')
            image.pixels.count { (_, c) -> c == '#' }
        }

    private fun enhance(image: Image): Image {
        val enhancedPixels = image.xRange.flatMap { x -> image.yRange.map { y -> Pixel(x, y) } }
            .associateWith { pixel -> enhanced(pixel, image) }
        val default = if (image.default == '.') algorithm.first() else algorithm.last()
        return Image(enhancedPixels, default)
    }

    private val Image.xRange get() = pixels.keys.minOf { it.x } - 1..pixels.keys.maxOf { it.x } + 1
    private val Image.yRange get() = pixels.keys.minOf { it.y } - 1..pixels.keys.maxOf { it.y } + 1

    private fun enhanced(pixel: Pixel, image: Image): Char {
        val ys = pixel.y - 1..pixel.y + 1
        val xs = pixel.x - 1..pixel.x + 1
        val index = ys.flatMap { y ->
            xs.map { x ->
                val c = image.pixels[Pixel(x, y)] ?: image.default
                if (c == '#') '1' else '0'
            }
        }.joinToString("").toInt(2)
        return algorithm[index]
    }

    class Image(val pixels: Map<Pixel, Char>, val default: Char = '.')
    data class Pixel(val x: Int, val y: Int)
}
