fun main() {
	val MOD = 1000000000
	val (n, k) = readLine()!!.split(" ").map { it.toInt() }

	val P = Array(n + 1) {IntArray(k + 1)}

	for (i in 0..n) {
		P[i][1] = 1
	}

	for (j in 1 until k) {
		for (i in 0..n) {
			for (m in 0..n) {
				if (i + m > n) break
				P[i + m][j + 1] += P[i][j]
				P[i + m][j + 1] %= MOD
			}
		}
	}
	println(P[n][k])
}