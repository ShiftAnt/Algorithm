import kotlin.math.max

fun main() {
	val n = readLine()!!.toInt()
	val P = readLine()!!.split(" ").map { it.toInt() }
	val al = Array(n) {ArrayList<Int>()}
	repeat(n - 1) {
		val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1}
		al[a] += b
		al[b] += a
	}
	val vstd = BooleanArray(n)
	val sub = Array(n) { 0 to 0 }
	fun dfs(idx: Int): Pair<Int, Int> {
		vstd[idx] = true
		var exc = 0
		var inc = P[idx]
		for (nxt in al[idx]) {
			if (vstd[nxt]) continue
			dfs(nxt).let {
				exc += max(it.first, it.second)
				inc += it.first
			}
		}
		sub[idx] = exc to inc
		return exc to inc
	}
	dfs(0).let {
		println(max(it.first, it.second))
	}
	vstd.fill(false)
	val ret = ArrayList<Int>()
	fun indi(idx: Int, prev: Boolean = false) {
		vstd[idx] = true
		if (!prev && sub[idx].second  >= sub[idx].first) ret += idx + 1

		for (nxt in al[idx]) {
			if (vstd[nxt]) continue
			if (!prev && sub[idx].second >= sub[idx].first) indi(nxt, true)
			else indi(nxt, false)
		}
	}
	indi(0, false)
	ret.sort()
	println(ret.joinToString(" "))
}