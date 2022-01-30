import java.io.*
import java.util.*

val dr = arrayOf(-1, 0, 1, 0)
val dc = arrayOf(0, 1, 0, -1)

class Node(
	val cnt: Int,
	val d: Int,
	val y: Int,
	val x: Int
)

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val n = br.readLine().toInt()

	val P = Array(n) { "" }

	val vstd = Array(n) { Array(n) { BooleanArray(4) } }

	var stt = 0 to 0

	repeat(n) {
		P[it] = br.readLine()
		for (i in 0 until n) {
			if (P[it][i] == '#') {
				stt = it to i
			}
		}
	}

	val pq = PriorityQueue<Node>(compareBy { it.cnt })

	repeat(4) {
		vstd[stt.first][stt.second][it] = true
		pq.add(Node(0, it, stt.first, stt.second))
	}
	var ret = 0

	while (!pq.isEmpty()) {
		val cur = pq.poll()

		val nxt = Node(cur.cnt, cur.d, cur.y + dr[cur.d], cur.x + dc[cur.d])

		if (nxt.y in 0 until n && nxt.x in 0 until n && !vstd[nxt.y][nxt.x][nxt.d] && P[nxt.y][nxt.x] != '*') {
			vstd[nxt.y][nxt.x][nxt.d] = true
			when (P[nxt.y][nxt.x]) {
				'#' -> {
					ret = nxt.cnt
					break
				}
				'!' -> {
					val dirs = arrayOf((nxt.d + 1) % 4, (nxt.d + 3) % 4)

					repeat(2) {
						if (!vstd[nxt.y][nxt.x][dirs[it]]) {
							vstd[nxt.y][nxt.x][dirs[it]] = true
							pq.add(Node(nxt.cnt + 1, dirs[it], nxt.y, nxt.x))
						}
					}
				}
			}
			pq.add(nxt)
		}
	}
	bw.write("$ret\n")
	bw.close()
	br.close()
}