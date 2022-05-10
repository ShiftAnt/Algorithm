fun main() {
	val (n, l) = readLine()!!.split(" ").map { it.toInt() }

	for (i in l..100) {
		val mid = n / i
		val stt = mid - (i - 1) / 2
		if (stt < 0) break
		val end = stt + i - 1
		val sub =  (stt + end) * i / 2
		if (sub == n) {
			for (ret in stt..end) {
				print("$ret ")
			}
			return
		}
	}
	println(-1)
}