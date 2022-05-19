fun main() {
	val st = java.util.StringTokenizer(readLine())
	val (s, n, k) = Array(3) {st.nextToken().toInt()}
	val (r1, r2, c1, c2) = Array(4) {st.nextToken().toInt()}
	val ret = StringBuilder()
	for (i in r1..r2) {
		loop@
		for (j in c1..c2) {
			var y = i
			var x = j
			val fp = ArrayList<Pair<Int, Int>>()
			fp += y to x
			for (k in 0 until s) {
				y /= n
				x /= n
				fp += y to x
			}
			fp.reverse()
			val idx = (n - k) / 2
			for (p in 1 until fp.size) {
				val cy = fp[p].first - fp[p - 1].first * n
				val cx = fp[p].second - fp[p - 1].second * n
				if (cy in idx until idx + k && cx in idx until idx + k) {
					ret.append("1")
					continue@loop
				}
			}
			ret.append("0")
		}
		ret.append("\n")
	}
	println(ret)
}