val ZERO = 0.toBigInteger()
val ONE = 1.toBigInteger()
val dr = arrayOf(0, 1)
val dc = arrayOf(1, 0)
fun main() {
	val n = readLine()!!.toInt()
	val P = Array(n) { readLine()!!.split(" ").map { it.toInt() } }
	val dp = Array(n) {Array(n) { ZERO } }
	dp[0][0] = ONE

	for (i in 0 until n) {
		for (j in 0 until n) {
			if (dp[i][j] != ZERO && P[i][j] != 0) {
				for (k in dr.indices) {
					val a = i + dr[k] * P[i][j]
					val b = j + dc[k] * P[i][j]
					if (a in 0 until n && b in 0 until n) {
						dp[a][b] += dp[i][j]
					}
				}
			}
		}
	}

	println(dp[n - 1][n - 1])
}