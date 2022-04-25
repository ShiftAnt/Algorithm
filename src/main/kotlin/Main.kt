import kotlin.math.*

val stt = 0 to 0
val end = 10000 to 10000
const val INF = Int.MAX_VALUE

fun fuel(a: Pair<Int, Int>, b: Pair<Int, Int>)
	= ((a.first - b.first).let { it * it } + (a.second - b.second).let { it * it }).let {
		val srt = sqrt(it.toDouble()).toInt()
	srt / 10 + if (it == srt * srt) 0 else 1
}
fun main() {
	val (n, k) = readLine()!!.split(" ").map { it.toInt() }

	val P = ArrayList<Pair<Int, Int>>()
	P += stt
	repeat(n) {
		P += readLine()!!.split(" ").map { it.toInt() }.let { it[0] to it[1] }
	}
	P += end
	val dp = Array(n + 2) {IntArray(k + 1) {INF} }
	val vstd = BooleanArray(n + 2)
	val que = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
	dp[0][0] = 0
	que += Triple(0, 0, 0)
	var ret = 0
	while (!que.isEmpty()) {
		val cur = que.poll()
		if (cur.second == n + 1) {
			ret = cur.first
			break
		}
		if (cur.first > dp[cur.second][cur.third] || vstd[cur.second]) continue
		vstd[cur.second] = true
		for (i in 1 until n + 2) {
			if (i == cur.second) continue
			val nxtK = cur.third + if (i == n + 1) 0 else 1
			if (nxtK > k) continue
			val nxtVal = max(fuel(P[cur.second], P[i]), cur.first)
			if (nxtVal < dp[i][nxtK]) {
				dp[i][nxtK] = nxtVal
				que += Triple(nxtVal, i, nxtK)
			}
		}
	}
	println(ret)
}