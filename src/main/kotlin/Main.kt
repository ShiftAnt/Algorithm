import java.io.*
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, m) = br.readLine().split(" ").map { it.toInt() }
	val que = java.util.PriorityQueue<Triple<Int, Int, Boolean>>(compareBy { !it.third })
	val P = Array(n) {StringBuilder(br.readLine())}

	for (i in 0 until n) {
		for (j in 0 until m) {
			when (P[i][j]) {
				'J' -> {
					que.add(Triple(i, j, false))
				}
				'F' -> que.add(Triple(i, j, true))
			}
		}
	}
	val tmp = ArrayDeque<Triple<Int, Int, Boolean>>()
	var ret = 0
	while (que.isNotEmpty()) {
		++ret
		for (tc in que.indices) {
			val cur = que.poll()
			for (i in dr.indices) {
				val y = cur.first + dr[i]
				val x = cur.second + dc[i]
				if (y in 0 until n && x in 0 until m) {
					if (cur.third && P[y][x] != '#' && P[y][x] != 'F') {
						P[y][x] = 'F'
						tmp.add(Triple(y, x, true))
					} else if (!cur.third && P[y][x] == '.') {
						P[y][x] = 'J'
						tmp.add(Triple(y, x, false))
					}
				} else if (!cur.third) {
					println(ret)
					return
				}
			}
		}
		while (tmp.isNotEmpty()) que.add(tmp.removeFirst())
	}
	println("IMPOSSIBLE")
}