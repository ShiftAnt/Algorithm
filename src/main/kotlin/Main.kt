import kotlin.math.*

fun check(num: Int) = sqrt(num.toDouble()).toInt().let { it * it == num }

fun main() {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }
	val P = Array(n) { readLine()!!.toCharArray().map { it - '0' } }
	var ret = -1
	fun move(r: Int, c: Int, a: Int, b: Int) {
		var y = r
		var x = c
		var cur = 0
		while (y in 0 until n && x in 0 until m) {
			cur = cur * 10 + P[y][x]
			if (check(cur)) ret = max(ret, cur)
			if (a == 0 && b == 0) return
			y += a
			x += b
		}
	}

	for (i in 0 until n) {
		for (j in 0 until m) {
			for (a in -n + 1 until n) {
				for (b in -m + 1 until m) {
					move(i, j, a, b)
				}
			}
		}
	}
	println(ret)
}