fun main() {
	val n = readLine()!!.toInt()
	val al = Array(n) {ArrayList<Int>()}
	repeat(n - 1) {
		readLine()!!.split(" ").map { it.toInt() - 1 }.let {
			al[it[1]] += it[0]
		}
	}
	val vstd = BooleanArray(n)
	fun dfs(idx: Int): Int {
		var ret = 1

		for (nxt in al[idx]) {
			if (!vstd[nxt]) {
				vstd[nxt] = true
				ret += dfs(nxt)
			}
		}
		return ret
	}
	var ret = -1
	for (i in 0 until n) {
		vstd.fill(false)
		if (dfs(i) == n) {
			ret = i + 1
			break
		}
	}
	println(ret)
}