fun main() {
	val n = readLine()!!.toInt()
	val P = readLine()!!.split(" ").map { it.toInt() }.sorted()

	println(P[0] * P[n - 1])
}