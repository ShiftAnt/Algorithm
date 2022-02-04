import java.io.*

fun calDif(a: Int, b: Int) = when (a) {
	-1 -> 0
	1 -> 3
	else -> when (b) {
		-1 -> 1
		1 -> 2
		else -> 4
	}
}
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (N, K, R) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(N) { IntArray(N) }

	repeat(R) {
		val a = br.readLine().split(" ").map { it.toInt() - 1 }
		P[a[0]][a[1]] += 1.shl(calDif(a[2] - a[0], a[3] - a[1]))
		P[a[2]][a[3]] += 1.shl(calDif(a[0] - a[2], a[1] - a[3]))
	}
	repeat(K) {
		val a = br.readLine().split(" ").map { it.toInt() - 1 }

		P[a[0]][a[1]] += 1.shl(4)
	}

	val vstd = Array(N) {BooleanArray(N)}
	val al = ArrayList<Int>()
	for (i in 0 until N) {
		for (j in 0 until N) {
			if (!vstd[i][j]) {
				val que = ArrayDeque<Pair<Int, Int>>()
				que += i to j
				var cnt = 0
				vstd[i][j] = true
				while (que.isNotEmpty()) {
					val cur = que.removeFirst()
					if (P[cur.first][cur.second].and(1.shl(4)) != 0) ++cnt
					for (k in 0 until 4) {
						val y = cur.first + dr[k]
						val x = cur.second + dc[k]

						if (y in 0 until N && x in 0 until N && !vstd[y][x] && P[cur.first][cur.second].and(1.shl(k)) == 0) {
							vstd[y][x] = true
							que.add(y to x)
						}
					}
				}
				al.add(cnt)
			}
		}
	}
	var ret = 0
	var tmp = K
	for (a in al) {
		tmp -= a
		ret += tmp * a
	}

	bw.write("$ret\n")


	bw.close()
	br.close()
}