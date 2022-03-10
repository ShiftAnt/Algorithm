fun main() {
	val P = Array(3) { readLine()!!.split(" ").map { it.toInt() }}
	println(((P[1][0] - P[0][0]) * (P[2][1] - P[0][1])).compareTo(((P[1][1] - P[0][1]) * (P[2][0] - P[0][0]))))
}