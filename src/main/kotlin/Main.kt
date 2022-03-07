import java.io.*

class Node(
	var like: Int = 0,
	var empty: Int = 0,
	var y: Int = Int.MAX_VALUE,
	var x: Int = Int.MAX_VALUE
)
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	val n = br.readLine().toInt()
	val n2 = n * n
	val P = Array(n2) {IntArray(4)}
	val ord = ArrayList<Int>()
	repeat(n2) {
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			for (i in 0 until 4) {
				P[it[0]][i] = it[i + 1]
			}
			ord.add(it[0])
		}
	}
	val Q = Array(n) {IntArray(n) {-1} }
	for (tc in ord) {
		var mx = Node()
		for (i in 0 until n) {
			for (j in 0 until n) {
				if (Q[i][j] != -1) continue
				val cur = Node(y = i, x = j)
				for (k in dr.indices) {
					val y = i + dr[k]
					val x = j + dc[k]
					if (y in 0 until n && x in 0 until n) {
						if (Q[y][x] == -1) ++cur.empty
						else if (Q[y][x] in P[tc]) ++cur.like
					}
				}
				if (mx.like < cur.like) mx = cur
				else if (mx.like == cur.like) {
					if (mx.empty < cur.empty) mx = cur
					else if (mx.empty == cur.empty) {
						if (mx.y > cur.y) mx = cur
						else if (mx.y == cur.y && mx.x > cur.x) mx = cur
					}
				}
			}
		}
		Q[mx.y][mx.x] = tc
	}
	var ret = 0
	for (i in 0 until n) {
		for (j in 0 until n) {
			var like = 0
			for (k in dr.indices) {
				val y = i + dr[k]
				val x = j + dc[k]
				if (y in 0 until n && x in 0 until n) {
					if (Q[y][x] in P[Q[i][j]]) ++like
				}
			}
			ret += when (like) {
				1 -> 1
				2 -> 10
				3 -> 100
				4 -> 1000
				else -> 0
			}
		}
	}
	println(ret)
}