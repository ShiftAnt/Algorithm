import java.io.*
import kotlin.math.abs

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	val n = br.readLine().toInt()
	val dif = 0 to 0
	val P = Array(3) {Array(n) {dif} }

	for (i in 0 until n) {
		val st = java.util.StringTokenizer(br.readLine())
		for (j in 0 until 3) {
			P[j][i] = st.nextToken().toInt() to i
		}
	}
	for (i in 0 until 3) P[i].sortBy { it.first }
	val que = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy{it.first})
	for (i in 0 until 3) {
		for (j in 0 until n - 1) {
			que += Triple(abs(P[i][j].first - P[i][j + 1].first), P[i][j].second, P[i][j + 1].second)
		}
	}
	val djs = IntArray(n) {it}
	val cnt = IntArray(n) {1}

	fun dis(idx: Int, set: Int = -1): Int {
		var cur = idx

		while (cur != djs[cur]) {
			val tmp = cur
			cur = djs[cur]
			if (set != -1) djs[tmp] = set
		}
		if (set != -1) djs[cur] = set
		return cur
	}
	var ret = 0
	while (que.isNotEmpty()) {
		val cur = que.poll()
		val da = dis(cur.second)
		val db = dis(cur.third)
		if (da == db) continue
		ret += cur.first
		if (cnt[da] > cnt[db]) {
			cnt[da] += cnt[db]
			dis(cur.second, da)
			dis(cur.third, da)
		} else {
			cnt[db] += cnt[da]
			dis(cur.second, db)
			dis(cur.third, db)
		}
	}
	println(ret)
}