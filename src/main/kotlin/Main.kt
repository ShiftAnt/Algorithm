import java.io.*
import kotlin.math.*

var n = 0
var m = 0
var f = 0
val que = ArrayDeque<IntArray>()
var vstd = arrayOf<BooleanArray>()
val fnd = ArrayList<IntArray>()
var cur = intArrayOf()
var Q = arrayOf<IntArray>()
var P = arrayOf<IntArray>()
fun init() {
	que.clear()
	vstd = Array(n) { BooleanArray(n) }
	fnd.clear()
}
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun bfs(target: Int): Int {
	que.add(cur)
	vstd[cur[0]][cur[1]] = true

	var step = 0
	while (!que.isEmpty()) {
		val siz = que.size
		for (tc in 0 until siz) {
			val cur = que.removeFirst()

			if (target < 0) {
				if (P[cur[0]][cur[1]] < 0) {
					fnd.add(cur)
				}
			} else {
				if (cur[0] == Q[target][2] && cur[1] == Q[target][3]) return step
			}

			for (i in 0 until 4) {
				val y = cur[0] + dr[i]
				val x = cur[1] + dc[i]

				if (y in 0 until n && x in 0 until n && !vstd[y][x] && P[y][x] != 1) {
					vstd[y][x] = true
					que.add(intArrayOf(y, x))
				}
			}
		}
		if (fnd.isNotEmpty()) {
			val mi = intArrayOf(n, n)

			for (ele in fnd) {
				if (mi[0] > ele[0]) {
					mi[0] = ele[0]
					mi[1] = ele[1]
				} else if (mi[0] == ele[0]) {
					mi[1] = min(mi[1], ele[1])
				}
			}
			val mie = -P[mi[0]][mi[1]] - 1
			if (step <= f) {
				f -= step
				init()
				cur[0] = Q[mie][0]
				cur[1] = Q[mie][1]
				P[cur[0]][cur[1]] = 0
			} else return -1
			val nxt = bfs(mie)
			if (nxt == -1) return -1
			return if (nxt <= f) {
				f += nxt
				init()
				cur[0] = Q[mie][2]
				cur[1] = Q[mie][3]
				--m
				f
			} else -1

		}
		++step
	}
	return -1
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
		f = it[2]
	}
	P = Array(n) { intArrayOf() }
	repeat(n) {
		P[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
	}
	cur = br.readLine().split(" ").map { it.toInt() - 1 }.toIntArray()
	Q = Array(m) { intArrayOf() }
	repeat(m) {
		Q[it] = br.readLine().split(" ").map { it.toInt() - 1 }.toIntArray()
		P[Q[it][0]][Q[it][1]] = -it - 1
	}
	init()

	while (m != 0) {
		if (bfs(-1) == -1) {
			break
		}
	}
	if (m != 0) bw.write("-1\n")
	else bw.write("$f\n")

	bw.close()
	br.close()
}