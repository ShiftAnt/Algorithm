import kotlin.math.*

val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) { readLine()!!.toCharArray().map { it - '0' }.toIntArray() }
	var ret = 0

	fun bfs(y: Int, x: Int) {
		val que = ArrayList<Pair<Int, Int>>()
		val vstd = Array(n) {BooleanArray(m)}
		vstd[y][x] = true
		que.add(y to x)

		var isFail = false
		var idx = -1
		var mi = Int.MAX_VALUE
		while(++idx < que.size) {
			val cur = que[idx]
			for (i in dr.indices) {
				val a = cur.first + dr[i]
				val b = cur.second + dc[i]
				if (vstd[a][b]) continue
				vstd[a][b] = true
				if (P[a][b] == -1) {
					isFail = true
					continue
				}
				if (P[y][x] == P[a][b]) que.add(a to b)
				else mi = min(mi, P[a][b])
			}
		}
		if (isFail) {
			que.forEach { P[it.first][it.second] = -1 }
			return
		}
		ret += que.size * (mi - P[y][x])
		que.forEach { P[it.first][it.second] = mi }
	}

	for (tc in 1 until 9) {
		for (i in 0 until n) {
			for (j in 0 until m) {
				if ((i == 0 || i == n - 1 || j == 0 || j == m - 1) && P[i][j] == tc) P[i][j] = -1
			}
		}
		for (i in 1 until n - 1) {
			for (j in 0 until m - 1) {
				if (P[i][j] == tc) {
					bfs(i, j)
				}
			}
		}
	}
	println(ret)
}