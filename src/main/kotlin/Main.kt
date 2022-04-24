import java.io.*
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (m, n, l) = br.readLine().split(" ").map { it.toInt() }

	val P = br.readLine().split(" ").map { it.toInt() }.sorted()

	fun findMi(num: Int): Int {
		if (num <= P[0]) return P[0]

		var stt = 0
		var end = P.size - 1
		var ret = Int.MAX_VALUE
		while (stt <= end) {
			val mid = (stt + end) / 2
			if (num <= P[mid]) {
				ret = P[mid]
				end = mid - 1
			} else {
				stt = mid + 1
			}
		}
		return ret
	}
	fun findMx(num: Int): Int {
		if (P.last() <= num) return P.last()

		var stt = 0
		var end = P.size - 1
		var ret = 0

		while (stt <= end) {
			val mid = (stt + end) / 2
			if (P[mid] <= num) {
				ret = P[mid]
				stt = mid + 1
			} else end = mid - 1
		}
		return ret
	}
	var ret = 0
	for (tc in 0 until n) {
		val (a, b) = br.readLine().split(" ").map { it.toInt() }
		if (b > l) continue
		val k = l - b
		val f = findMi(a - k)
		val t = findMx(a + k)
		if (f <= t) ++ret
	}
	println(ret)
}