import java.io.*
import java.util.PriorityQueue
import kotlin.math.*

fun dis(a: Pair<Long, Long>, b: Pair<Long, Long>): Double {
	val y = a.first - b.first
	val x = a.second - b.second
	return sqrt(y.toDouble() * y + x * x)
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, m) = br.readLine().split(" ").map { it.toInt() }

	val pnts = Array(n) {0L to 0L}

	repeat(n) { it ->
		pnts[it] = br.readLine().split(" ").map { it.toLong() }.let { e ->
			e[0] to e[1]
		}
	}
	val P = Array(n) {DoubleArray(n)}

	for (i in 0 until n) {
		for (j in i + 1 until n) {
			P[i][j] = dis(pnts[i], pnts[j])
			P[j][i] = P[i][j]
		}
	}
	repeat(m) {
		val (a, b) = br.readLine().split(" ").map { it.toInt() - 1 }

		P[a][b] = 0.0
		P[b][a] = 0.0
	}
	val que = PriorityQueue<Pair<Int, Double>>(compareBy { it.second })
	val vstd = BooleanArray(n)
	que.add(0 to 0.0)
	var ret = 0.0
	while (!que.isEmpty()) {
		val idx = que.peek().first
		val num = que.poll().second

		if (vstd[idx]) continue
		vstd[idx] = true
		ret += num
		for (i in 0 until n) {
			if (idx != i && !vstd[i]) {
				que.add(i to P[idx][i])
			}
		}
	}
	bw.write(String.format("%.2f", ret))
	bw.close()
	br.close()
}