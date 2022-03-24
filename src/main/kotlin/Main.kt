const val INF = Int.MAX_VALUE
fun main() {
	val (n, k) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) { readLine()!!.split(" ").map { it.toInt() } }

	val dp = Array(1.shl(n)) {IntArray(n) {INF} }
	dp[1.shl(k)][k] = 0
	val que = ArrayDeque<Pair<Int, Int>>()
	que.add(1.shl(k) to k)
	while (que.isNotEmpty()) {
		val cur = que.removeFirst()
		for (i in 0 until n) {
			val nxtVstd = cur.first.or(1.shl(i))
			if (dp[nxtVstd][i] > dp[cur.first][cur.second] + P[cur.second][i]) {
				dp[nxtVstd][i] = dp[cur.first][cur.second] + P[cur.second][i]
				que.add(nxtVstd to i)
			}
		}
	}
	println(dp[1.shl(n) - 1].minOf { it })
}