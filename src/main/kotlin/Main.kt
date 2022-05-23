val n = 8

fun sToI(s: String) = intArrayOf(s[1] - '1', s[0] - 'A')

fun iToS(i: IntArray) = "${'A' + i[1]}${'1' + i[0]}"

val dr = arrayOf(0, 0, -1, 1, 1, 1, -1, -1)
val dc = arrayOf(1, -1, 0, 0, 1, -1, 1, -1)

fun main() {
	val (y, x, M) = readLine()!!.split(" ")

	val m = M.toInt()

	val P = Array(n) {IntArray(n)}

	val a = sToI(y)
	val b = sToI(x)

	P[a[0]][a[1]] = -1
	P[b[0]][b[1]] = 1

	repeat(m) {
		val dir = when (readLine()) {
			"R" -> 0
			"L" -> 1
			"B" -> 2
			"T" -> 3
			"RT" -> 4
			"LT" -> 5
			"RB" -> 6
			"LB" -> 7
			else -> -1
		}
		val ny = a[0] + dr[dir]
		val nx = a[1] + dc[dir]
		try {
			if (ny in 0 until n && nx in 0 until n) {
				if (P[ny][nx] == 1) {
					P[b[0] + dr[dir]][b[1] + dc[dir]] = 1
					b[0] += dr[dir]
					b[1] += dc[dir]
				}
				P[a[0]][a[1]] = 0
				a[0] = ny
				a[1] = nx
				P[ny][nx] = -1
			}
		} catch (_: Exception) {}
	}
	println(iToS(a))
	println(iToS(b))
}