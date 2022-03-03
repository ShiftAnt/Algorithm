import java.io.*
import java.util.PriorityQueue
import kotlin.math.*

const val INF = Long.MAX_VALUE
const val NONE = Long.MIN_VALUE
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	val (n, m, d, e) = br.readLine().split(" ").map { it.toInt() }

	val al = Array(n) {ArrayList<Pair<Int, Int>>()}

	val h = br.readLine().split(" ").map { it.toLong() }

	repeat(m) {
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			al[it[0]].add(it[1] to it[2] + 1)
			al[it[1]].add(it[0] to it[2] + 1)
		}
	}
	val que = Array(2) {(PriorityQueue<Pair<Int, Long>>(compareBy { it.second }))}

	val dis = Array(2) {LongArray(n)}
    repeat(dis.size) {
        dis[it].fill(INF)
    }
	dis[0][0] = 0
	dis[1][n - 1] = 0

	que[0].add(0 to 0L)
	que[1].add(n - 1 to 0L)
	val vstd = Array(2) {BooleanArray(n)}
	var ret = NONE
	for (T in 1 downTo 0) {
		while (!que[T].isEmpty()) {
			val cur = que[T].peek().first
			val num = que[T].poll().second
			if (vstd[T][cur]) continue
			vstd[T][cur] = true

			if (T == 0 && dis[1][cur] != INF) {
				ret = max(ret, (h[cur] * e - (dis[0][cur] + dis[1][cur]) * d))
			}
			for (nxt in al[cur]) {
				if (h[cur] >= h[nxt.first]) continue

				val nxtVal = num + nxt.second
				if (dis[T][nxt.first] > nxtVal) {
					dis[T][nxt.first] = nxtVal
					que[T].add(nxt.first to nxtVal)
				}
			}
		}
	}
	println(if (ret == NONE) "Impossible" else ret)
}