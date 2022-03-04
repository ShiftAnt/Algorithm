import java.io.*
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)

fun twoPow(cnt: Int, sub: Long = 2L): Long {
	var ret = 1L
    if (cnt == 0) return ret
    if (cnt % 2 == 1) ret *= sub
	ret %= MOD
    ret *= twoPow(cnt / 2, (sub * sub) % MOD)
    return ret % MOD
}
const val MOD = 1_000_000_007
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, m) = br.readLine().split(" ").map { it.toInt() }
	val P = Array(n) {br.readLine()}
	var ret = 0L
	for (i in 0 until n) {
		loop@
		for (j in 0 until m) {
			for (k in dr.indices) {
				val y = i + dr[k]
				val x = j + dc[k]
				if (y in 0 until n && x in 0 until m && P[i][j] != P[y][x]) continue@loop
			}
			++ret
		}
	}
	ret = twoPow(ret.toInt())

	println(ret)
}