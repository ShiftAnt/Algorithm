import kotlin.math.*

class Node(
	val idx: Int,
	var isFirst: Boolean = false,
	var num: Long = 0,
)

fun main() {
	val n = readLine()!!.toInt()
	val strs = Array(n) { readLine()!! }
	val P = Array(10) {Node(it)}

	for (str in strs) {
		for (i in str.length - 1 downTo 0) {
			val idx = str[i] - 'A'
			val cur = str.length - 1 - i
			P[idx].num += 10.0.pow(cur).toLong()
			if (i == 0) P[idx].isFirst = true
		}
	}
	P.sortByDescending {it.num}

	var ret = 0L
	var total = 0
	var notFirst = -1

	for (i in P.indices) {
		if (P[i].num != 0L) ++total
		if (!P[i].isFirst) notFirst = i
	}
	if (total == 10) {
		for (i in notFirst until P.size - 1) {
			P[i] = P[i + 1].also { P[i + 1] = P[i] }
		}
	}
	for (i in 0 until total) {
		ret += P[i].num * (9 - i)
	}
	println(ret)
}