var n = 0
lateinit var P: Array<IntArray>
lateinit var dp: Array<IntArray>
const val INF = Int.MAX_VALUE
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)

fun solution(): Int {
	val que = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
	dp[0][0] = P[0][0]
	que += Triple(0, 0, P[0][0])
	while (que.isNotEmpty()) {
		val cur = que.poll()
		val y = cur.first
		val x = cur.second
		if (dp[y][x] < cur.third) continue
		if (y == n - 1 && x == n - 1) break
		for (i in dr.indices) {
			val a = y + dr[i]
			val b = x + dc[i]
			if (a !in 0 until n || b !in 0 until n || dp[a][b] <= dp[y][x] + P[a][b]) continue
			dp[a][b] = dp[y][x] + P[a][b]
			que += Triple(a, b, dp[a][b])
		}
	}
	return dp[n - 1][n - 1]
}

fun main() {
	var tc = 0
	while (true) {
		++tc
		n = readLine()!!.toInt()
		if (n == 0) break
		P = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
		dp = Array(n) { IntArray(n) {INF} }
		println("Problem $tc: ${solution()}")
	}
}