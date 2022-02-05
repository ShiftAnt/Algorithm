import java.io.*

var n = 0
var q = 0
val al = Array(6) {ArrayList<String>()}

fun last(cur: ArrayList<String>, a: String): Int {
	var s = 0
	var e = cur.size - 1

	while (s <= e) {
		val mid = (s + e) / 2
		if (cur[mid] <= a) {
			if (mid == cur.size - 1 || cur[mid + 1] > a) return mid
			s = mid + 1
		} else e = mid - 1
	}
	return -1
}

fun least(cur: ArrayList<String>, a: String): Int {
	var s = 0
	var e = cur.size - 1

	while (s <= e) {
		val mid = (s + e) / 2
		if (cur[mid] >= a) {
			if (mid == 0 || cur[mid - 1] < a) return mid
			e = mid - 1
		} else {
			s = mid + 1
		}
	}
	return -1
}

fun fnd(stt: String, end: String, lv: Int): Int {
	var ret = 0
	for (t in lv - 1 until 6) {
		val si = least(al[t], stt)
		ret += if (si == -1) 0 else last(al[t], end) - si + 1
	}
	return ret
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		q = it[1]
	}
	repeat(n) {
		br.readLine().split("#").let {
			al[it[1].toInt() - 1] += it[0]
		}
	}
	repeat(6) {
		al[it].sort()
	}

	repeat(q) {
		br.readLine().split("#").let {
			bw.write("${fnd(it[0], it[1], it[2].toInt())}\n")
		}
	}
	bw.close()
	br.close()
}