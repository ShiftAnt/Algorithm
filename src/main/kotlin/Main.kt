fun main() {
	val n = readLine()!!.toInt()
	val P = IntArray(n) { readLine()!!.toInt() }

	val st = java.util.Stack<Pair<Int, Int>>()
	var ret = 0L
	for (i in n - 1 downTo 0) {
		val cur = i to P[i]

		while (st.isNotEmpty() && cur.second > st.peek().second) {
			st.pop()
		}
		ret += (if (st.isEmpty()) n else st.peek().first) - 1 - cur.first

		st += cur
	}
	println(ret)
}