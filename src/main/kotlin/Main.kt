import java.io.*
val dr = arrayOf(0, 1, 1, 1)
val dc = arrayOf(1, -1, 0, 1)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val n = 3
	while (true) {
		val cur = br.readLine()
		if (cur == "end") break
		val P = Array(n) {CharArray(n)}

		repeat(9) {
			P[it / n][it % n] = cur[it]
		}
		var o = 0
		var x = 0
		for (i in 0 until n) {
			for (j in 0 until n) {
				if (P[i][j] == 'O') ++o
				else if (P[i][j] == 'X') ++x
			}
		}
		if (x - o != 0 && x - o != 1) {
			bw.write("invalid\n")
			continue
		}
		var co = false
		var cx = false
		for (i in 0 until n) {
			for (j in 0 until n) {
				if (P[i][j] == '.') continue
				for (k in dr.indices) {
					val y = i + dr[k] * (n - 1)
					val x = j + dc[k] * (n - 1)
					if (y in 0 until n && x in 0 until n && P[i][j] == P[y][x] && P[i][j] == P[i + dr[k]][j + dc[k]]) {
                        if (P[i][j] == 'O') co = true
                        else cx = true
					}
				}
			}
		}
		var ret = "valid"
		if (co && cx || cx && x == o || co && o != x || !co && !cx && o + x != n * n) ret = "invalid"
		bw.write("$ret\n")
	}
	bw.flush()
}