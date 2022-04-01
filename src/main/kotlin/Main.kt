import kotlin.math.*

fun main() {
	val n = readLine()!!.toInt()
	val strs = Array(n) { readLine()!! }
	val P = Array(10) { longArrayOf(0, 0) }

	for (str in strs) {
		for (i in str.indices) {
			val alpha = str[i] - 'A'
			if (i == 0) P[alpha][1] = 1
			P[alpha][0] += 10.0.pow(str.length - 1 - i).toLong()
		}
	}
	P.sortByDescending {it[0]}

	var ret = 0L
	var total = 0
	var notFirst = -1

	for (i in P.indices) {
		if (P[i][0] != 0L) ++total
		if (P[i][1] == 0L) notFirst = i
	}
	if (total == 10) {
		for (i in notFirst until 9) {
			P[i] = P[i + 1].also { P[i + 1] = P[i] }
		}
	}
	for (i in 0 until total) {
		ret += P[i][0] * (9 - i)
	}
	println(ret)
}