import java.io.*
val dr = arrayOf(-1, -1, -1, 0, 0, 0, 1, 1, 1)
val dc = arrayOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = 8
	val P = Array(n + 1) {Array(n) {""} }


	repeat(n) {
		P[0][it] = br.readLine()
	}

	for (idx in 1..n) {
			P[idx][0] = "........"
		for (i in 1 until n) {
			P[idx][i] = P[idx - 1][i - 1]
		}
	}
	val vstd = Array(n + 1) {Array(n) {BooleanArray(n)} }

	val que = ArrayDeque<Triple<Int, Int, Int>>()
	que.add(Triple(0, n - 1, 0))

	vstd[0][n - 1][0] = true
	var ret = 0
	loop@
	while (!que.isEmpty()) {
		val cur = que.removeFirst()

		val nxt = (cur.first + 1).let { if (it > 8) 8 else it }
		for (i in dr.indices) {
			val y = cur.second + dr[i]
			val x = cur.third + dc[i]

			if (y in 0 until n && x in 0 until n && !vstd[nxt][y][x] && P[cur.first][y][x] == '.') {
				if (y == 0 && x == n - 1) {
					ret = 1
					break@loop
				}
				if (P[nxt][y][x] == '#') continue
				vstd[nxt][y][x] = true
				que.add(Triple(nxt, y, x))
			}
		}
	}
	println(ret)
}