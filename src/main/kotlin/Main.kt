import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, p, k) = br.readLine().split(" ").map { it.toInt() }
	val P = Array(n) {IntArray(k + 1) {Int.MAX_VALUE} }
	val al = Array(n) {ArrayList<Pair<Int, Int>>()}
	repeat(p) {
		br.readLine().split(" ").map { it.toInt() }.let {
			al[it[0] - 1].add(it[1] - 1 to it[2])
			al[it[1] - 1].add(it[0] - 1 to it[2])
		}
	}
	P[0][0] = 0
	val que = ArrayDeque<Pair<Int, Int>>()
	que.add(0 to 0)

	while (que.isNotEmpty()) {
		val cur = que.removeFirst()
		for (nxt in al[cur.first]) {
			val mx = max(P[cur.first][cur.second], nxt.second)
			if (P[nxt.first][cur.second] > mx) {
				P[nxt.first][cur.second] = mx
				que.add(nxt.first to cur.second)
			}
			if (cur.second < k) {
				if (P[nxt.first][cur.second + 1] > P[cur.first][cur.second]) {
					P[nxt.first][cur.second + 1] = P[cur.first][cur.second]
					que.add(nxt.first to cur.second + 1)
				}
			}
		}
	}
	val ret = P[n - 1].minOf { it }.let { if (it == Int.MAX_VALUE) -1 else it}
	println(ret)
}