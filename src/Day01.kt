import kotlin.math.abs

fun main() {
    val re = Regex("^(\\d+)\\s+(\\d+)$")
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        for (line in input) {
            val m = re.find(line)
            if (m != null) {
                val (a, b) = m.destructured
                left.add(a.toInt())
                right.add(b.toInt())
            }
        }

        return left.sorted().zip(right.sorted()).sumOf { (a, b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val left = mutableMapOf<Int, Int>()
        val right = mutableMapOf<Int, Int>()
        for (line in input) {
            val m = re.find(line)
            if (m != null) {
                val a = m.groupValues[1].toInt()
                val b = m.groupValues[2].toInt()
                left[a]?.let { left[a] = it + 1 } ?: left.put(a, 1)
                right[b]?.let { right[b] = it + 1 } ?: right.put(b, 1)
            }
        }

        return left.keys.sumOf { k -> (k * (left[k] ?: 0) * (right[k] ?: 0)) };
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_sample")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
