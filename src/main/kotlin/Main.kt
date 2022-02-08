import java.io.*
import kotlin.math.max

var n = 0
var m = 0
var P = arrayOf<IntArray>()
var ret = 0

fun range(y: Int, x: Int) = y in 0 until n && x in 0 until m && P[y][x] != 0

fun check(y: Int, x: Int, sy: Int, sx: Int, ey: Int, ex: Int, sum: Int) {
	if (range(sy, sx) && range(ey, ex)) {
		val tmp = arrayOf(P[y][x], P[sy][sx], P[ey][ex])
		P[y][x] = 0
		P[sy][sx] = 0
		P[ey][ex] = 0
		dfs(y * m + x + 1, sum + tmp[0] * 2 + tmp[1] + tmp[2])
		P[y][x] = tmp[0]
		P[sy][sx] = tmp[1]
		P[ey][ex] = tmp[2]
	}
}

fun dfs(idx: Int, sum: Int) {
	if (idx == n * m) {
		ret = max(ret, sum)
		return
	}
	val y = idx / m
	val x = idx % m

	if (P[y][x] != 0) {
		check(y, x, y - 1, x, y, x - 1, sum)
		check(y, x, y - 1, x, y, x + 1, sum)
		check(y, x, y + 1, x, y, x - 1, sum)
		check(y, x, y + 1, x, y, x + 1, sum)
	}

	dfs(idx + 1, sum)
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
	}
	P = Array(n) { IntArray(0) }

	repeat(n) {
		P[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
	}
	dfs(0, 0)
	bw.write("$ret\n")
	bw.close()
	br.close()
}