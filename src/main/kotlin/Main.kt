val dr = arrayOf(-1, 0, 1, 0)
val dc = arrayOf(0, 1, 0, -1)
val dd = arrayOf(1, 3)
fun changeDir(d: Int) = when (d) {
	3 -> 0
	0 -> 1
	2 -> 2
	1 -> 3
	else -> -1
}
fun solution(): Int {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) { readLine()!!.split(" ").map { it.toInt() } }
	val que = ArrayDeque<Triple<Int, Int, Int>>()
	val vstd = Array(n) {Array(m) {BooleanArray(4)} }
	val se = Array(2) { readLine()!!.split(" ").map { it.toInt() - 1 }.let {
		Triple(it[0], it[1], changeDir(it[2]))
	} }

	if (se[0] == se[1]) return 0
	que += se[0]
	vstd[se[0].first][se[0].second][se[0].third] = true
	var ret = 0
	while (que.isNotEmpty()) {
		++ret
		for (tc in que.indices) {
			val cur = que.removeFirst()

			for (i in 1..3) {
				val y = cur.first + dr[cur.third] * i
				val x = cur.second + dc[cur.third] * i
				if (y !in 0 until n || x !in 0 until m || vstd[y][x][cur.third]) continue
				if (P[y][x] != 0) break
				val nxt = Triple(y, x, cur.third)
				if (nxt == se[1]) return ret
				vstd[nxt.first][nxt.second][nxt.third] = true
				que += nxt
			}

			for (i in dd.indices) {
				val d = (cur.third + dd[i]) % 4
				val nxt = Triple(cur.first, cur.second, d)
				if (nxt == se[1]) return ret
				if (vstd[nxt.first][nxt.second][nxt.third]) continue
				vstd[nxt.first][nxt.second][nxt.third] = true
				que += nxt
			}
		}
	}
	return -1
}

fun main() {
	println(solution())
}