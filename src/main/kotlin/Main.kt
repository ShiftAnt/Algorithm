import kotlin.math.*

fun main() {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(m) { readLine()!!.split(" ").map {it.toInt()} }
	val sub = intArrayOf(n % 6, 6)
	val mi = intArrayOf(P[0][0], P[0][0])

	for (i in 0 until m) {
		for (j in sub.indices) {
			mi[j] = min(mi[j], P[i][0])
			mi[j] = min(mi[j], P[i][1] * sub[j])
		}
	}
	var ret = 0
	if (sub[0] != 0) ret += mi[0]
	ret += n / 6 * mi[1]
	println(ret)
}