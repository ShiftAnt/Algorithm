fun main() {
	IntArray(readLine()!!.toInt()).also {
		readLine()!!.split(" ").mapIndexed { i, s -> s.toInt() to i }
			.sortedWith(compareBy({ it.first }, { it.second }))
			.forEachIndexed { i, ele -> it[ele.second] = i }
	}.forEach { print("$it ") }
}