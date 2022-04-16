fun main() {
	val (n, c) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) { readLine()!!.toLong() }.sorted()

	var a = 1L
	var b = P[n - 1] - P[0]
	var ret = 0L
	while (a <= b) {
		val mid = (a + b) / 2
		var prev = P[0]
		var idx = 1
		var isUp = true
		loop@
		for (tc in 0 until c - 2) {
			if (idx == n - 1) {
				isUp = false
				break
			}
			while (P[idx] - prev < mid) if (++idx == n - 1) {
				isUp = false
				break@loop
			}
			prev = P[idx++]
		}
		if (isUp && P[n - 1] - P[idx - 1] < mid) isUp = false

		if (isUp) {
			ret = mid
			a = mid + 1
		} else b = mid - 1
	}
	println(ret)
}