import java.io.*

val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(n) {StringBuilder()}

	repeat(n) {
		P[it] = StringBuilder(br.readLine())
	}

	val que = ArrayDeque<Triple<Int, Int, Int>>()

	val vstd = Array(n) {Array(m) {BooleanArray(k + 1)} }

	vstd[0][0][k] = true

	que.add(Triple(0, 0, k))
	var cnt = 0
	var ret = -1
	loop@
	while (!que.isEmpty()) {
		++cnt
		for (tc in que.indices) {
			val cur = que.removeFirst()
			val z = cur.third
			if (cur.first == n - 1 && cur.second == m - 1) {
				ret = cnt
				break@loop
			}
			for (i in 0 until 4) {
				val y = cur.first + dr[i]
				val x = cur.second + dc[i]

				if (y in 0 until n && x in 0 until m) {
					if (P[y][x] == '0' && !vstd[y][x][z]) {
						vstd[y][x][z] = true
						que.add(Triple(y, x, z))
					}
					if (P[y][x] == '1' && z > 0 && !vstd[y][x][z - 1]) {
						vstd[y][x][z - 1] = true
						que.add(Triple(y, x, z - 1))
					}
				}
			}
		}
	}
	bw.write("$ret")
	bw.close()
	br.close()
}