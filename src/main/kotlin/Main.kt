import java.io.*

val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (R, C, N) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(R) {IntArray(C)}

	repeat(R) {
		val tmp = br.readLine()
		for (i in 0 until C) {
			P[it][i] = -1
			if (tmp[i] == 'O') P[it][i] = 0
		}
	}

	for (tc in 2..N) {
		if (tc % 2 == 0) {
			for (i in 0 until R) {
				for (j in 0 until C) {
					if (P[i][j] == -1) P[i][j] = tc
				}
			}
		} else {
			for (i in 0 until R) {
				for (j in 0 until C) {
					if (P[i][j] == tc - 3) {
						for (k in dr.indices) {
							try {
								val cur = P[i + dr[k]][j + dc[k]]
								if (cur != P[i][j]) P[i + dr[k]][j + dc[k]] = -1
							} catch (_: IndexOutOfBoundsException) {}
						}
						P[i][j] = -1
					}
				}
			}
		}
	}
	for (i in 0 until R) {
		for (j in 0 until C) {
			bw.write(if (P[i][j] == -1) "." else "O")
		}
		bw.write("\n")
	}


	bw.close()
	br.close()
}