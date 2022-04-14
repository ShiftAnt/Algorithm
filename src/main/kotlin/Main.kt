import java.io.*
const val INF = Int.MAX_VALUE / 2
fun solution(n: Int, m: Int, k: Int) {
	val al = Array(n + 1) {ArrayList<Triple<Int, Int, Int>>()}
	repeat(k) {
		val (a, b, c, d) = br.readLine().split(" ").map { it.toInt() }
		al[a] += Triple(b, c, d)
	}
	val P = Array(n + 1) {IntArray(m + 1) {INF} }
	P[1][0] = 0

	val que = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy {it.third})
	que += Triple(1, 0, 0)

	while (que.isNotEmpty()) {
		val cur = que.poll()
		if (cur.first == n) {
			bw.write("${cur.third}\n")
			return
		}
		if (cur.third > P[cur.first][cur.second]) continue
		for (nxt in al[cur.first]) {
			val nxtFee = cur.second + nxt.second
			val nxtVal = cur.third + nxt.third
			if (nxtFee > m || P[nxt.first][nxtFee] <= nxtVal) continue
			P[nxt.first][nxtFee] = nxtVal
			que += Triple(nxt.first, nxtFee, nxtVal)
		}
	}
	bw.write("Poor KCM\n")
}
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
	repeat(br.readLine().toInt()) {
		br.readLine().split(" ").map { it.toInt() }.let {
			solution(it[0], it[1], it[2])
		}
	}
	bw.flush()
}