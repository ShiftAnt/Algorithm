import java.io.*
import kotlin.math.max

var n = 0
var m = 0
lateinit var P: Array<IntArray>
lateinit var vstd: Array<IntArray>

val dr = arrayOf(0, -1, 0, 1)
val dc = arrayOf(-1, 0, 1, 0)

fun bfs(y: Int, x: Int, idx: Int): Int {
	val que = ArrayDeque<Pair<Int, Int>>()
	que.add(y to x)
	vstd[y][x] = idx
	var ret = 1
	while (que.isNotEmpty()) {
		val cur = que.removeFirst()

		for (i in 0 until 4) {
			val a = cur.first + dr[i]
			val b = cur.second + dc[i]

			if (P[cur.first][cur.second].and(1.shl(i)) == 0 && vstd[a][b] == 0) {
				vstd[a][b] = idx
				que.add(a to b)
				++ret
			}
		}
	}
	return ret
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.run {
		n = this[1]
		m = this[0]
	}

	P = Array(n) { intArrayOf() }
	repeat(n) {
		P[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
	}
	vstd = Array(n) { IntArray(m) }
	var idx = 0

	val al = ArrayList<Int>()
	for (i in 0 until n) {
		for (j in 0 until m) {
			if (vstd[i][j] == 0) {
				al.add(bfs(i, j, ++idx))
			}
		}
	}
	val a = al.size
	val b = al.maxOf { it }
	var c = b

	for (i in 0 until n) {
		for (j in 0 until m) {
			val cur = vstd[i][j]
			for (k in 2 until 4) {
				val y = i + dr[k]
				val x = j + dc[k]
				if (y in 0 until n && x in 0 until m && cur != vstd[y][x]) {
					c = max(c, al[cur - 1] + al[vstd[y][x] - 1])
				}
			}
		}
	}
	bw.write("$a\n$b\n$c\n")
	bw.close()
	br.close()
}