import java.io.*
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = br.readLine().toInt()
	val n2 = n * 2
	val P = Array(n) {Array(n2) {0 to 0} }

	var idx = 0
	val loc = ArrayList<Array<Pair<Int, Int>>>()
	repeat(n) {
		var i = if (it % 2 == 0) 0 else 1

		for (tc in 0 until if (it % 2 == 0) n else n - 1) {
			val (a, b) = br.readLine().split(" ").map { it.toInt() }
			P[it][i++] = a to idx
			P[it][i++] = b to idx++
			loc.add(arrayOf(it to i - 2, it to i - 1))
		}
	}
	val que = ArrayDeque<Int>()
	val vstd = BooleanArray(idx)
	val prev = IntArray(idx) {-1}
	que += 0
	vstd[0] = true

	while (que.isNotEmpty()) {
		val ci = que.removeFirst()
		val cur = loc[ci]
		for (sub in cur) {
			for (i in dr.indices) {
				val y = sub.first + dr[i]
				val x = sub.second + dc[i]
				if (y in 0 until n && x in 0 until n2 && P[sub.first][sub.second].first == P[y][x].first) {
					val nxt = P[y][x].second
					if (vstd[nxt]) continue
					prev[nxt] = ci
					vstd[nxt] = true
					que.add(nxt)
				}
			}
		}
	}
	var cur = 0
	for (i in idx - 1 downTo 0) {
		if (prev[i] != -1) {
			cur = i
			break
		}
	}
	val ret = ArrayList<Int>().also {
		while (prev[cur] != -1) {
			it += cur + 1
			cur = prev[cur]
		}
		if (cur == 0) it += 1
	}
	ret.reverse()
	println(ret.size)
	println(ret.joinToString(" "))
}