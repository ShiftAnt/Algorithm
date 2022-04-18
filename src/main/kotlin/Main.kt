fun main() {
	val n = readLine()!!.toInt()
	val P = IntArray(n) { readLine()!!.toInt() - 1 }

	val vstd = BooleanArray(n)
	val ret = ArrayList<Int>()
	for (i in 0 until n) {
		val tmp = BooleanArray(n)
		var cur = i
		while (!tmp[cur]) {
			tmp[cur] = true
			cur = P[cur]
		}
		if (vstd[cur]) continue
		while (!vstd[cur]) {
			vstd[cur] = true
			ret += cur
			cur = P[cur]
		}
	}
	println(ret.size)
	println(ret.sorted().map { it + 1 }.joinToString ("\n"))
}