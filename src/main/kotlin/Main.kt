fun main() {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }

	val P = IntArray(n) { readLine()!!.toInt() }

	var stt = 1L
	var end = P.minOf { it }.toLong() * m
	var ret = end
	while (stt <= end) {
		val mid = (stt + end) / 2

		var sum = 0L

		for (p in P) sum += mid / p
		if (sum >= m) {
			ret = mid
			end = mid - 1
		} else {
			stt = mid + 1
		}
	}
	println(ret)
}