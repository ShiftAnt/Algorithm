fun main() {
	val (n, k) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) {IntArray(n)}
	repeat(k) {
		readLine()!!.split(" ").map { it.toInt() - 1 }.let {
			P[it[0]][it[1]] = -1
			P[it[1]][it[0]] = 1
		}
	}
	for (m in 0 until n) {
		for (i in 0 until n) {
			for (j in 0 until n) {
				if (P[i][m] == P[m][j] && P[i][m] != 0) P[i][j] = P[i][m]
			}
		}
	}
	repeat(readLine()!!.toInt()) {
		val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }
		println(P[a][b])
	}
}