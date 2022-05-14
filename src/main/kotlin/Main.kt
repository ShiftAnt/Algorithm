class Fraction(
	val a: Int,
	val b: Int
) {
	operator fun compareTo(other: Fraction) =
		(this.a.toLong() * other.b - other.a.toLong() * this.b).let {
			if (it > 0) 1 else if (it < 0) -1 else 0
		}
}

fun main() {
	val n = readLine()!!.toInt()

	val P = readLine()!!.split(" ").map { it.toInt() }
	val ret = IntArray(n)
	for (i in 0 until n) {
		var mx = Fraction(0, 0)
		for (r in i + 1 until n) {
			val cur = Fraction(P[r] - P[i], r - i)
			mx = if (r == i + 1) {
				cur
			} else if (cur > mx) cur
			else continue
			if (cur.a > 0) {
				++ret[i]
				++ret[r]
			}
		}
		for (l in i - 1 downTo 0) {
			val cur = Fraction(P[l] - P[i], i - l)
			mx = if (l == i - 1) cur else if (cur > mx) cur else continue
			if (cur.a >= 0) {
				++ret[i]
				++ret[l]
			}
		}
	}
	println(ret.maxOf { it })
}