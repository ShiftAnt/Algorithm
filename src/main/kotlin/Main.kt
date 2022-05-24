fun main() {
	val n = readLine()!!.toInt()
	var stt = -1

	val P = readLine()!!.split(" ").map { it.toInt() }

	val al = Array(n) { ArrayList<Int>() }

	repeat(n) {
		if (P[it] == -1) stt = it
		else al[P[it]] += it
	}
	val vstd = BooleanArray(n)
	var ret = 0
	val except = readLine()!!.toInt()
	fun dfs(idx: Int): Int {
		vstd[idx] = true
		if (idx == except) return 0
		var child = 1

		for (nxt in al[idx]) {
			if (vstd[nxt]) continue
			child += dfs(nxt)
		}
		if (child == 1) ++ret
		return child
	}
	dfs(stt)
	println(ret)
}