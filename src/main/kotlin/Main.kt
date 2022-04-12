fun main() {
	val (h, w, x, y) = readLine()!!.split(" ").map { it.toInt() }

	val P = Array(h + x) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

	for (i in 0 until h) {
		for (j in 0 until w) {
			P[i + x][j + y] -= P[i][j]
			print("${P[i][j]} ")
		}
		print("\n")
	}
}