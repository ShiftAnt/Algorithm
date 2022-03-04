import java.io.*
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)

fun twoPow(cnt: Int): Long {
	var ret = 1L
	var sub = 2L
	var tmp = cnt
	while (tmp != 0) {
		if (tmp % 2 == 1) ret *= sub
		tmp /= 2
		ret %= MOD
		sub *= sub
		sub %= MOD
	}
	return ret
}
const val MOD = 1_000_000_007
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, m) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(n) {br.readLine()}

	val inside = Array(n) {BooleanArray(m)}

	for (i in 0 until n) {
		loop@
		for (j in 0 until m) {
			for (k in dr.indices) {
				val y = i + dr[k]
				val x = j + dc[k]
				if (y in 0 until n && x in 0 until m && P[i][j] != P[y][x]) continue@loop
			}
			inside[i][j] = true
		}
	}
	var ret = 1L
	val vstd = Array(n) {BooleanArray(m)}
	val que = ArrayDeque<Pair<Int, Int>>()
	for (i in 0 until n) {
		for (j in 0 until m) {
			if (!vstd[i][j] && inside[i][j]) {
				var sum = 0
				vstd[i][j] = true
				que.add(i to j)
				while (!que.isEmpty()) {
					val cur = que.removeFirst()
					++sum
					for (k in dr.indices) {
						val y = cur.first + dr[k]
						val x = cur.second + dc[k]
						if (y in 0 until n && x in 0 until m && !vstd[y][x] && P[y][x] == P[i][j] && inside[y][x]) {
							vstd[y][x] = true
							que.add(y to x)
						}
					}
				}
				ret *= twoPow(sum)
				ret %= MOD
			}
		}
	}
	println(ret)
}