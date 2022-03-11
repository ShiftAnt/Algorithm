import java.io.*

val dr = arrayOf(0, 1, 0, -1)
val dc = arrayOf(-1, 0, 1, 0)
val sy = arrayOf(
	arrayOf(-1, 1, -1, 1, -2, 2, -1, 1, 0),
	arrayOf(0, 0, 1, 1, 1, 1, 2, 2, 3),
	arrayOf(-1, 1, -1, 1, -2, 2, -1, 1, 0),
	arrayOf(0, 0, -1, -1, -1, -1, -2, -2, -3)
	)
val sx = arrayOf(
	arrayOf(0, 0, -1, -1, -1, -1, -2, -2, -3),
	arrayOf(-1, 1, -1, 1, -2, 2, -1, 1, 0),
	arrayOf(0, 0, 1, 1, 1, 1, 2, 2, 3),
	arrayOf(-1, 1, -1, 1, -2, 2, -1, 1, 0)
	)
val prcnt = arrayOf(1, 1, 7, 7, 2, 2, 10, 10, 5)
var ret = 0

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = br.readLine().toInt()
	val P = Array(n) {br.readLine().split(" ").map { it.toInt() }.toIntArray()}
	var y = n / 2
	var x = n / 2
	var d = 0
	var mx = 1
	var step = 0
	var isTwo = false
	while (y != 0 || x != 0) {
		val ny = y + dr[d]
		val nx = x + dc[d]
		val snd = P[ny][nx]
		var sum = 0
		P[ny][nx] = 0

		for (i in sy[d].indices) {
			val a = y + sy[d][i]
			val b = x + sx[d][i]
			val cur = snd * prcnt[i] / 100
			sum += cur
			if (a in 0 until n && b in 0 until n) P[a][b] += cur
			else ret += cur
		}
		val rem = snd - sum

		val a = ny + dr[d]
		val b = nx + dc[d]

		if (a in 0 until n && b in 0 until n) P[a][b] += rem
		else ret += rem

		if (step + 1 == mx) {
			step = 0
			if (isTwo) mx += 1
			isTwo = !isTwo
			d = (d + 1) % 4
		} else step += 1
		y = ny
		x = nx
	}
	println(ret)
}