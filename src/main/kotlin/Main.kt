import java.io.*

val dr = arrayOf(1, 0, -1, 0)
val dc = arrayOf(0, 1, 0, -1)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (A, B) = br.readLine().split(" ").map { it.toInt() }

	val (N, M) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(B) {IntArray(A)}
	val rbt = ArrayList<Pair<Int, Int>>()
	repeat(N) {
		val cur = br.readLine().split(" ")
		val y = cur[1].toInt() - 1
		val x = cur[0].toInt() - 1

		P[y][x] = when (cur[2]) {
			"N" -> 1
			"E" -> 2
			"S" -> 3
			else -> 4
		}
		rbt.add(y to x)
	}
	var ret = "OK"

	for (i in 0 until M) {
		val cur = br.readLine().split(" ")
		if (ret != "OK") continue
		val k = cur[0].toInt() - 1

		loop@
		for (rpt in 0 until cur[2].toInt()) {
			val y = rbt[k].first
			val x = rbt[k].second
			when (cur[1]) {
				"L" -> P[y][x] = if (P[y][x] == 1) 4 else P[y][x] - 1
				"R" -> P[y][x] = if (P[y][x] == 4) 1 else P[y][x] + 1
				else -> {
					val a = y + dr[P[y][x] - 1]
					val b = x + dc[P[y][x] - 1]
					if (a in 0 until B && b in 0 until A) {
						if (P[a][b] != 0) {
							for (rb in rbt.indices) {
								if (rbt[rb].first == a && rbt[rb].second == b) {
									ret ="Robot ${k + 1} crashes into robot ${rb + 1}"
									break@loop
								}
							}
						} else {
							P[a][b] = P[y][x]
							P[y][x] = 0
							rbt[k] = a to b
						}
					} else {
						ret = "Robot ${k + 1} crashes into the wall"
						break@loop
					}
				}
			}
		}

	}
	bw.write(ret)
	bw.close()
	br.close()
}