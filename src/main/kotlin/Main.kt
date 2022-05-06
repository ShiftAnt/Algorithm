import kotlin.math.*
fun find(r: Int, c: Int): Int {
	val n = max(abs(r), abs(c))

	val lt = 4 * n * n + 1
	val rd = lt + 4 * n

	if (r == n) return rd - (n - c)
	if (c == n) return lt - 2 * n - (r + n)
	if (r == -n) return lt - (c + n)
	return lt + (r + n)
}

fun blank(str: String, num: Int) =  StringBuilder().also { sb ->
	repeat(num - str.length) {
		sb.append(" ")
	}
	sb.append(str)
}.toString()

fun main() {
	val (r1, c1, r2, c2) = readLine()!!.split(" ").map { it.toInt() }
	val n = r2 - r1 + 1
	val m = c2 - c1 + 1
	val P = Array(n) {IntArray(m)}
	var mx = 0
	for (i in r1..r2) {
		for (j in c1..c2) {
			P[i - r1][j - c1] = find(i, j)
			mx = max(mx, P[i - r1][j - c1])
		}
	}
	mx = mx.toString().length
	for (i in 0 until n) {
		for (j in 0 until m) {
			print("${blank(P[i][j].toString(), mx)} ")
		}
		print("\n")
	}
}