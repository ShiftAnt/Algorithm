fun main() {
	val n = readLine()!!.toInt()
	val st = java.util.StringTokenizer(readLine())
	val P = IntArray(n) {st.nextToken().toInt()}

	val ret = IntArray(n)
	var sub = 0
	while (P.sumOf { it } != 0) {
		for (i in P.indices) {
			if (P[i] != 0) {
				++sub
				if (--P[i] == 0) ret[i] = sub
			}
		}
	}
	println(ret.joinToString(" "))
}